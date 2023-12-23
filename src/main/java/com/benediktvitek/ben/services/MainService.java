package com.benediktvitek.ben.services;


import com.benediktvitek.ben.scraping.Scraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final Scraper scraper;

    public List<String> getMovies() throws IOException {
        return scraper.parseHtml(scraper.scrapeSite()); //call to scrape hard coded url and return list of movies
    }



}
