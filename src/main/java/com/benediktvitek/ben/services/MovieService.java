package com.benediktvitek.ben.services;

import com.benediktvitek.ben.dtos.MovieDto;
import com.benediktvitek.ben.dtos.MovieListDTO;
import com.benediktvitek.ben.scraping.Scraper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class MovieService {

    private final Scraper scraper;

    @Value("${API_TOKEN}")
    private String token;


    public List<MovieDto> getRatedMovies(List<String> movies) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();
        List<MovieDto> moviesWithRatings = new ArrayList<>();
        MovieListDTO movieListDTO;

        for (String movie : movies) {
            String encodedMovie = scraper.encodeMovieName(movie);
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/search/movie?query=" + encodedMovie + "&include_adult=false&language=en-US&page=1")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", token)
                    .build();

            try (Response response = okHttpClient.newCall(request).execute();) {
                if (response.isSuccessful() && response.body() != null) {
                    movieListDTO = objectMapper.readValue(response.body().string(), MovieListDTO.class);
                    moviesWithRatings.add(newestRelease(movieListDTO));
                }
            } catch (IOException e) {
                System.out.println("Exception: " + e);
            }

        }
        return moviesWithRatings;
    }

    public MovieDto newestRelease(MovieListDTO movieListDTO) {

        return movieListDTO.results().stream()
                .max(Comparator.comparing(MovieDto::id))
                .orElse(null);
    }


}