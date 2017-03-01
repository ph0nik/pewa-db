package com.pewa.movie;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;
import com.pewa.config.ConfigReader;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

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
                Set<String> genres = new TreeSet<>(Arrays.asList(omdbValues.asObject().getString("Genre", "").split(", ")));
                movieItem.setGenre(genres);
                Set<String> dir = new TreeSet<>(Arrays.asList(omdbValues.asObject().getString("Director", "").split(", ")));
                movieItem.setDirector(dir);
                Set<String> writer = new TreeSet<>(Arrays.asList(omdbValues.asObject().getString("Writer", "").split(", ")));
                movieItem.setWriter(writer);
                Set<String> actors = new TreeSet<>(Arrays.asList(omdbValues.asObject().getString("Actors", "").split(", ")));
                movieItem.setActors(actors);
                movieItem.setPlot(omdbValues.asObject().getString("Plot", ""));
                Set<String> language = new TreeSet<>(Arrays.asList(omdbValues.asObject().getString("Language", "").split(", ")));
                movieItem.setLanguage(language);
                Set<String> country = new TreeSet<>(Arrays.asList(omdbValues.asObject().getString("Country", "").split(", ")));
                movieItem.setCountry(country);
                movieItem.setAwards(omdbValues.asObject().getString("Awards", ""));
                movieItem.setPoster(omdbValues.asObject().getString("Poster", ""));
                movieItem.setMetascore(omdbValues.asObject().getString("Metascore", ""));
                movieItem.setImdbRating(omdbValues.asObject().getString("imdbRating", ""));
                movieItem.setImdbVotes(omdbValues.asObject().getString("imdbVotes", ""));
                movieItem.setImdbID(imdbUrl);
            }

        } catch (
                IOException e) {
            System.out.println("Nie można nawiązać połączenia z: " + url);
        }
        return movieItem;
    }
}
