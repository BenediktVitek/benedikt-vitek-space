package com.benediktvitek.ben.services;

import com.benediktvitek.ben.dtos.responses.MovieDTO;
import com.benediktvitek.ben.dtos.requests.MovieListDTO;
import com.benediktvitek.ben.scraping.Scraper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class MovieService {

    private final Scraper scraper;

    @Value("${API_TOKEN}")
    private String token;


    public List<MovieDTO> getTopTenMoviesDto() throws IOException {
        int numTries = 5;
        List<MovieDTO> moviesWithRatings = new ArrayList<>();
        List<String> movies = new ArrayList<>();
        while (true) {
            try {
                movies = scraper.getScrapedMovies();
                break;
            } catch (Exception e ) {
                System.out.println(e.getMessage());
                if (--numTries == 0) throw e;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        MovieListDTO movieListDTO;

        for (String movie : movies) {
            String encodedMovie = encodeMovieName(movie);
            Request request = new Request.Builder()
                     .url("https://api.themoviedb.org/3/search/movie?query=" + encodedMovie + "&include_adult=false&language=en-US&page=1")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", token)
                    .build();

            try (Response response = okHttpClient.newCall(request).execute();) {
                if (response.isSuccessful() && response.body() != null) {
                    movieListDTO = objectMapper.readValue(response.body().string(), MovieListDTO.class);
                    moviesWithRatings.add(movieListDTO.results().get(0));
                }
            } catch (IOException e) {
                throw new RuntimeException("Exception movie service: " + e.getMessage());
            }
        }
        return moviesWithRatings;
    }


    public String encodeMovieName(String name) {
        String encoded = URLEncoder.encode(name, StandardCharsets.UTF_8);
        return encoded.replace("+", "%20");
    }

}