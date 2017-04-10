package com.pewa.movie;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;
import com.pewa.*;
import com.pewa.common.Country;
import com.pewa.common.Genre;
import com.pewa.common.Language;
import com.pewa.common.Person;
import com.pewa.config.ConfigFactory;
import com.pewa.util.SaveImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class MovieParser implements MediaParse<Movie, String> {

    private static final Logger log = LogManager.getLogger(MovieParser.class);

    public Movie getItem(String imdbUrl) {
        Movie movieItem = new Movie();
        try {
            final String url = ConfigFactory.get("item.omdbLink").concat(imdbUrl);
            String omdbItem = Jsoup.connect(url)
                    .userAgent(ConfigFactory.get("search.userAgent"))
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .text();
            JsonValue omdbValues = Json.parse(omdbItem);
            String error = omdbValues.asObject().getString("Response", "");
            if (error.equals("False")) {
                log.error(error);
            } else {
                movieItem.setTitle(omdbValues.asObject().getString("Title", ""));
                movieItem.setYear(Integer.parseInt(omdbValues.asObject().getString("Year", "")));
                movieItem.setAgeRating(omdbValues.asObject().getString("Rated", ""));
                movieItem.setRelDate(omdbValues.asObject().getString("Released", ""));
                movieItem.setRuntime(omdbValues.asObject().getString("Runtime", ""));
                List<String> genresList = Arrays.asList(omdbValues.asObject().getString("Genre","").split(", "));
                genresList.forEach(x -> movieItem.setGenres(new Genre(x)));
                List<String> dirList = Arrays.asList(omdbValues.asObject().getString("Director", "").split(", "));
                dirList.forEach(x -> movieItem.setStaff(new Person(x,"","director")));
                List<String> writerList = Arrays.asList(omdbValues.asObject().getString("Writer", "").split(", "));
                writerList.forEach(x -> movieItem.setStaff(new Person(x,"","writer")));
                List<String> actorList = Arrays.asList(omdbValues.asObject().getString("Actors", "").split(", "));
                actorList.forEach(x -> movieItem.setStaff(new Person(x,"","actor")));
                movieItem.setPlot(omdbValues.asObject().getString("Plot", ""));
                List<String> langList = Arrays.asList(omdbValues.asObject().getString("Language", "").split(", "));
                langList.forEach(x -> movieItem.setLanguage(new Language(x)));
                List<String> countryList = Arrays.asList(omdbValues.asObject().getString("Country", "").split(", "));
                countryList.forEach(x -> movieItem.setCountry(new Country(x)));
                movieItem.setAwards(omdbValues.asObject().getString("Awards", ""));
                movieItem.setPoster(omdbValues.asObject().getString("Poster", ""));
                movieItem.setMetascore(omdbValues.asObject().getString("Metascore", ""));
                movieItem.setImdbRating(omdbValues.asObject().getString("imdbRating", ""));
                movieItem.setImdbVotes(omdbValues.asObject().getString("imdbVotes", ""));
                movieItem.setImdbID(imdbUrl);
                movieItem.setType(PewaType.MOVIE);
                movieItem.setIntPoster(SaveImage.getImage(movieItem));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
        return movieItem;
    }
}
