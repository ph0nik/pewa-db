package com.pewa.movie;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.pewa.*;
import com.pewa.common.Country;
import com.pewa.common.Genre;
import com.pewa.common.Language;
import com.pewa.common.Person;
import com.pewa.config.ConfigFactory;
import com.pewa.util.SaveImage;
//import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.apache.commons.codec.language.bm.Lang;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class MovieParser implements MediaParse<Movie, String> {

    private static final Logger log = LogManager.getLogger(MovieParser.class);
    private MovieSearch movieSearch = new MovieSearchTheMovieDatabase();

    @Override
    public Movie getItem(String id) {
        Movie movieitem = new Movie();
        final String url = ConfigFactory.get("myapifilms-item.url")
                .replaceAll("<imdbid>",id)
                .replaceAll("<token>",ConfigFactory.get("myapifilms-item.token"));
        try {
            String responseString = Jsoup.connect(url)
                    .userAgent(ConfigFactory.get("search.userAgent"))
                    .timeout(30 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .text();
            log.info(url);
            JsonObject responseJson = Json.parse(responseString).asObject();
            List<String> error = responseJson.asObject().names();
            if (error.contains("error")) {
                log.error(error);
            } else {
                movieitem = parseMyApiFilmsItem(responseJson, movieitem);
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return movieitem;
    }

    public Movie parseMyApiFilmsItem(JsonObject responseJson, Movie movieItem) {
        JsonValue jsonValue = responseJson
                .get(ConfigFactory.get("myapifilms-json.data"))
                .asObject()
                .get(ConfigFactory.get("myapifilms-json.movies"))
                .asArray()
                .get(0);
        String titlePL = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.titlePl"), "");
        String titleEng = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.titleOrg"), titlePL);
        String year = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.year"), "");
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        String preformatterDate = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.releaseDate"), "");
        LocalDate releaseDate = LocalDate.parse(preformatterDate, formatter);
        JsonArray directorsArray = jsonValue
                .asObject()
                .get(ConfigFactory.get("myapifilms-json.dir"))
                .asArray();
        List<Person> directorsList = new ArrayList<>();
        for (JsonValue director : directorsArray) {
            directorsList.add(
                    new Person(director.asObject()
                            .getString(ConfigFactory.get("myapifilms-json.directorName"), "")
                            , ""
                            , "director")
            );
        }
        JsonArray writersArray = jsonValue
                .asObject()
                .get(ConfigFactory.get("myapifilms-json.wri"))
                .asArray();
        List<Person> writersList = new ArrayList<>();
        for (JsonValue writer : writersArray) {
            writersList.add(
                    new Person(writer.asObject()
                            .getString(ConfigFactory.get("myapifilms-json.directorName"), "")
                            , ""
                            , "writer")
            );
        }
        String runtime = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.runtime"), "");
        String externalPoster = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.poster"), "");
        JsonArray countriesArray = jsonValue
                .asObject()
                .get(ConfigFactory.get("myapifilms-json.countries"))
                .asArray();
        Set<Country> countriesSet = new TreeSet<>();
        for (JsonValue country : countriesArray) {
            countriesSet.add(
                    new Country(country.asString())
            );
        }
        JsonArray languageArray = jsonValue
                .asObject()
                .get(ConfigFactory.get("myapifilms-json.languages"))
                .asArray();
        Set<Language> languageSet = new TreeSet<>();
        for (JsonValue language : languageArray) {
            languageSet.add(
                    new Language(language.asString())
            );
        }
        JsonArray genreArray = jsonValue
                .asObject()
                .get(ConfigFactory.get("myapifilms-json.genres"))
                .asArray();
        Set<Genre> genreSet = new TreeSet<>();
        for (JsonValue genre : genreArray) {
            genreSet.add(
                    new Genre(genre.asString())
            );
        }
        String ageRating = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.ageRating"),"");
        String plot = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.plot"), "");
        JsonArray actorsArray = jsonValue
                .asObject()
                .get(ConfigFactory.get("myapifilms-json.act"))
                .asArray();
        Set<Person> actorsSet = new TreeSet<>();
        for (JsonValue actor : actorsArray) {
            actorsSet.add(
                    new Person(actor.asObject().getString(ConfigFactory.get("myapifilms-json.actorName"), "")
                            , ""
                            , "actor")
            );
        }
        String imdbId = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.externalMovieId"), "");
        String imdbRating = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.imdbRating"), "");
        String imdbVotes = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.imdbVotes"), "");
        String metascore = jsonValue
                .asObject()
                .getString(ConfigFactory.get("myapifilms-json.metascore"), "");
        movieItem.setTitlePl(titlePL);
        if (titleEng.isEmpty()) {
            movieItem.setTitle(titlePL);
        } else {
            movieItem.setTitle(titleEng);
        }
        movieItem.setYear(Integer.parseInt(year));
        movieItem.setRelDate(releaseDate);
        for (Person dir : directorsList) {
            movieItem.setStaff(dir);
        }
        for (Person wri : writersList) {
            movieItem.setStaff(wri);
        }
        for (Person act : actorsSet) {
            movieItem.setStaff(act);
        }
//        movieItem.setRuntime(runtime);
        movieItem.setCountry(countriesSet);
        movieItem.setLanguage(languageSet);
        movieItem.setGenres(genreSet);
        movieItem.setPlot(plot);
        movieItem.setImdbID(idImdbString2Int(imdbId));
        movieItem.setPoster(externalPoster);
        movieItem.setIntPoster(SaveImage.getImage(movieItem));
        movieItem.setType(PewaType.MOVIE);

        return movieItem;
    }

    private Integer idImdbString2Int(String idImdb) {
        return (idImdb.isEmpty() || idImdb.length() == 0) ? 0 : Integer.parseInt(idImdb.replace("tt","11"));
    }

}
