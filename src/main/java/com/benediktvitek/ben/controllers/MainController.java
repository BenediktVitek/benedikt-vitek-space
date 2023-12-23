package com.benediktvitek.ben.controllers;

import com.benediktvitek.ben.dtos.MovieDto;
import com.benediktvitek.ben.dtos.MovieListDTO;
import com.benediktvitek.ben.scraping.Scraper;
import com.benediktvitek.ben.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final Scraper scraper;
    private final MovieService movieService;

    @GetMapping("/index")
    public String index() {
        List<String> movies;
        List<MovieDto> moviesWithRatings = null;
        try {
            movies = scraper.parseHtml(scraper.scrapeSite());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String movie : movies) {
            System.out.println(movie);
        }

        try {
            moviesWithRatings = movieService.getRatedMovies(movies);
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());;
        }

        if(moviesWithRatings == null) {
            System.out.println("No movies returned");
        } else {
            for (MovieDto movie: moviesWithRatings) {
                System.out.println("ID: " + movie.id() + " , title: " + movie.title() + " , rating: " + movie.vote_average());
            }
        }

        return "index";
    }


}
