package com.pewa.controller;

import com.pewa.MediaParse;
import com.pewa.common.*;
import com.pewa.movie.MovieDAO;
import com.pewa.movie.MovieSearch;
import com.pewa.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by phonik on 2017-03-02.
 */

@RestController
@RequestMapping("/movie/")
public class MovieController {

    @Autowired
    private MovieSearch movieSearch;

    @Autowired
    private MovieDAO movieDao;

    @Autowired
    @Qualifier(value = "movieParser")
    private MediaParse<Movie, String> movieParse;

    @Autowired
    private Results results;

    private Movie movie;

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results addMovie(@RequestBody Request imdbId) {
        movie = movieParse.getItem(imdbId.getImdbId());
        movieDao.addMovie(movie);
        return results;
    }

    @GetMapping(value = "search/{query}")
    public Set<SingleSearchResult> searchExternal(@PathVariable String query) {
        Set<SingleSearchResult> resultSet = movieSearch.externalMovieSearch(query);
        return resultSet;
    }

    @GetMapping(value = "searchdb/{query}")
    public Results searchDb(@PathVariable String query) {
        return movieDao.moviesByTitle(query, results).setMessage();
    }

    /*
    * zapytanie POST w body musi mieć jsona, json może mieć tylko jeden pametr, np. {"id":4} i też zostanie prawidłowo zmapowany
    *
    * */
    @PostMapping(value = "person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByPerson(@RequestBody Person person) {
        movieDao.moviesByPersonId(person, results);
        return results.setMessage();

    }

    @PostMapping(value = "genre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByGenre(@RequestBody Genre genre) {
        movieDao.moviesByGenreId(genre, results);
        return results.setMessage();
    }

    @PostMapping(value = "lang", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByLanguage(@RequestBody Language lang) {
        movieDao.moviesByLanguageId(lang, results);
        return results.setMessage();
    }

    @PostMapping(value = "date", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByDate(@RequestBody Request dateSearch) {
        movieDao.moviesByYear(dateSearch.getDateIn(), dateSearch.getDateOut(), results);
        return results.setMessage();

    }


}
