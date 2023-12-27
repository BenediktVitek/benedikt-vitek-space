package com.benediktvitek.ben.scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class Scraper {

    private final String URL = "https://www.netflix.com/tudum/top10/united-states";
    private final String ELEMENT = ".tbl-cell-name";

    public String scrapeSite() throws IOException {

        URL obj = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5,0");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public List<String> parseHtml(String html) {
        Document doc = Jsoup.parse(html);
        Elements data = doc.select(ELEMENT);
        List<String> movies = new ArrayList<>();

        for (Element el : data) {
            movies.add(el.text());
        }
        return movies;
    }

    public List<String> getScrapedMovies() throws IOException {
        return parseHtml(scrapeSite());
    }


}
