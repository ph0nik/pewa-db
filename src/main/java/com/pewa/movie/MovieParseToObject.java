package com.pewa.movie;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;
import com.pewa.config.ConfigReader;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Arrays;

public class MovieParseToObject {

    private MovieParseToObject() {

    }

    public static Movie parseSelected(String imdbUrl) {
        String url = "";
        Movie movieItem = new Movie();

        try {
            url = ConfigReader.omdbUrl.concat(imdbUrl);
            String omdbItem = Jsoup.connect(url)
                    .userAgent(ConfigReader.userAgent)
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .text();
            JsonValue omdbValues = Json.parse(omdbItem);

            String error;
            error = omdbValues.asObject().getString("Response", "");
            if (error.equals("False")) {
                System.out.println("Wrong imdb id");
            } else {

                movieItem.setTitle(omdbValues.asObject().getString("Title", ""));
                movieItem.setYear(Integer.parseInt(omdbValues.asObject().getString("Year", "")));
                movieItem.setAgeRating(omdbValues.asObject().getString("Rated", ""));
                movieItem.setRelDate(omdbValues.asObject().getString("Released", ""));
                movieItem.setRuntime(omdbValues.asObject().getString("Runtime", ""));
                movieItem.setGenre(omdbValues.asObject().getString("Genre", "").split(", "));
                movieItem.setDirector(Arrays.asList(omdbValues.asObject().getString("Director", "").split(", ")));
                movieItem.setWriter(Arrays.asList(omdbValues.asObject().getString("Writer", "").split(", ")));
                movieItem.setActors(Arrays.asList(omdbValues.asObject().getString("Actors", "").split(", ")));
                movieItem.setPlot(omdbValues.asObject().getString("Plot", ""));
                movieItem.setLanguage(omdbValues.asObject().getString("Language", "").split(", "));
                movieItem.setCountry(omdbValues.asObject().getString("Country", "").split(", "));
                movieItem.setAwards(omdbValues.asObject().getString("Awards", ""));
                movieItem.setPoster(omdbValues.asObject().getString("Poster", ""));
                movieItem.setMetascore(omdbValues.asObject().getString("Metascore", ""));
                movieItem.setImdbRating(omdbValues.asObject().getString("imdbRating", ""));
                movieItem.setImdbVotes(omdbValues.asObject().getString("imdbVotes", ""));
//                movieItem.setImdbID(omdbValues.asObject().getString("imdbID",""));
                movieItem.setImdbID(imdbUrl);
//                return movieItem;
            }

        } catch (
                IOException e) {
            System.out.println("Nie można nawiązać połączenia z: " + url);
        }
        return movieItem;
    }
}
