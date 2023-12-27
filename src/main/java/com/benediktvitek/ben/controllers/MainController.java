package com.benediktvitek.ben.controllers;

import com.benediktvitek.ben.dtos.responses.MovieDTO;
import com.benediktvitek.ben.scraping.Scraper;
import com.benediktvitek.ben.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final Scraper scraper;
    private final MovieService movieService;

    @GetMapping("/index")
    public String index(Model model) {

        List<MovieDTO> moviesWithRatings = null;

        try {
            moviesWithRatings = movieService.getTopTenMoviesDto();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        model.addAttribute("movieList", moviesWithRatings);

        return "index";
    }


}
