package com.pewa.movie;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;
import com.neovisionaries.i18n.LanguageAlpha3Code;
import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.config.ConfigFactory;
import com.pewa.movie.tmdb.TmdbItem;
import com.pewa.util.SaveImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by phonik on 2017-10-18.
 */
@Component
public class MovieParserTmdb implements MediaParse<Movie, Integer> {

    private static final Logger log = LogManager.getLogger(MovieParserTmdb.class);
    private MovieSearch movieSearch = new MovieSearchTheMovieDatabase();
    
    @Override
    public Movie getItem(Integer request) {
        Movie movieitem = new Movie();
        movieitem.setTmdbId(request);
        final String url = ConfigFactory.get("themoviedb.item")
                .replaceAll("<id>", request.toString())
                .replaceAll("<api-key>", ConfigFactory.get("themoviedb.apiKey"));
        try {
            String responseString = Jsoup.connect(url)
                    .userAgent(ConfigFactory.get("search.userAgentMB"))
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .text();
            log.debug(url);

            JsonObject responseJson = Json.parse(responseString).asObject();
            List<String> error = responseJson.asObject().names();
            if (error.contains("error")) {
                log.error(error);
            } else {
                movieitem = parseTmdb(responseString, movieitem);
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return movieitem;
        }
        return movieitem;
    }

    private Movie parseTmdb(String responseJson, Movie movieitem) {
        Gson gson = new Gson();
        TmdbItem tmdbItem = gson.fromJson(responseJson, TmdbItem.class);
        movieitem.setImdbID(idImdbString2Int(tmdbItem.getImdbId()));
        movieitem.setTitle(tmdbItem.getOriginalTitle());
        movieitem.setEngTitle(tmdbItem.getTitle());
        tmdbItem.getAlternativeTitles().getTitles().forEach(t -> {
            if (t.getIso31661().equals("PL")) movieitem.setTitlePl(t.getTitle());
        });
        if (movieitem.getTitlePl() == null) movieitem.setTitlePl(movieitem.getTitle());
        movieitem.setPlot(tmdbItem.getOverview());
        movieitem.setPoster(tmdbItem.getPosterPath());
        movieitem.setIntPoster(SaveImage.getImage(movieitem));
        tmdbItem.getCredits().getCast().forEach(a -> movieitem.setStaff(new Person(a.getName(), "", "actor")));
        tmdbItem.getCredits().getCrew().forEach(s -> {
            String role = s.getJob().toLowerCase();
            if (role.equals("director") || role.equals("screenplay") || role.equals("writer")|| role.equals("original music composer"))
                movieitem.setStaff(new Person(s.getName(), "", role));
        });
        tmdbItem.getSpokenLanguages().forEach(l -> {
            String languageNameByCode;
            try {
                languageNameByCode = LanguageAlpha3Code.getByCodeIgnoreCase(l.getIso6391()).getName();
            } catch (NullPointerException ex) {
                log.error("[" + ex.toString() + "] @ " + LanguageAlpha3Code.class);
                languageNameByCode = l.getName();
            }
            movieitem.setLanguage(new Language(languageNameByCode));
        });
        tmdbItem.getProductionCountries().forEach(c -> movieitem.setCountry(new Country(c.getName())));
        tmdbItem.getGenres().forEach(g -> movieitem.setGenres(new Genre(g.getName())));
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate releaseDate = LocalDate.parse(tmdbItem.getReleaseDate(), formatter);
        movieitem.setRelDate(releaseDate);
        movieitem.setTmdbRating(tmdbItem.getVoteAverage());
        movieitem.setYear(movieitem.getRelDate().getYear());
        movieitem.setType(PewaType.MOVIE);
        movieitem.setRuntime(tmdbItem.getRuntime());
        return movieitem;
    }

    public Integer idImdbString2Int(String idImdb) {
        return (idImdb.isEmpty() || idImdb.length() == 0) ? 0 : Integer.parseInt(idImdb.replace("tt","11"));
    }
}
