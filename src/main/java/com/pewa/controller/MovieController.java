package com.pewa.controller;

import com.pewa.InitAllTables;
import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.movie.Movie;
import com.pewa.movie.MovieDAO;
import com.pewa.movie.MovieSearch;
import com.pewa.request.StatusRequest;
import com.pewa.status.Status;
import com.pewa.status.StatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by phonik on 2017-03-02.
 */

@RestController
@RequestMapping("/movie/")
public class MovieController {

    @Autowired
    @Qualifier(value = "movieSearchTheMovieDatabase")
    private MovieSearch movieSearch;

    @Autowired
    private MovieDAO movieDao;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    @Qualifier(value = "movieParserTmdb")
    private MediaParse<Movie, Integer> movieParse;

    @Autowired
    private Request request;

    private final PewaType movieType = PewaType.MOVIE;
    private final String json = MediaType.APPLICATION_JSON_VALUE;
    private final String missingParameters = "Missing parameters: ";
    private final String emptyStatus = "Error: Empty status";
    private Results results;
    private Status status;
    private Movie movie;

    @GetMapping(value = "search/{query}")
    public Results searchExternal(@PathVariable String query) {
        return movieSearch
                .externalMovieSearch(query, new Results())
                .setReturnMessage();
    }

    @GetMapping(value = "searchdb/{query}")
    public Results searchDb(@PathVariable String query) {
        return movieDao.moviesByTitle(query);
    }

    @GetMapping(value = "id/{id}")
    public Results searchById(@PathVariable Integer id) {
        return movieDao.moviesById(id);
    }


    @PostMapping(value = "add", consumes = json)
    public Results addMovie(@RequestBody StatusRequest request) {
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else {
            movie = movieParse.getItem(request.getEncounterId());
            Results addMovieResults = movieDao.addMovie(movie);
            Results addStatusResults = statusDAO.addStatus(request.extractStatus());
            addMovieResults.setRowsAffected(addMovieResults.getRowsAffected() + addStatusResults.getRowsAffected());
            addMovieResults.setReturnMessage(addMovieResults.getMessage() + "; " + addStatusResults.getMessage());
            return addMovieResults;
        }
    }

    @GetMapping(value = "update/{id}")
    public Results updateMovie(@PathVariable Integer id) {
        movie = movieParse.getItem(id);
        return movieDao.updateMovie(movie);
    }

    @GetMapping(value = "delete/{id}")
    public Results deleteMovie(@PathVariable Integer id) {
        return movieDao.deleteMovie(id);
    }

    @GetMapping(value = "personId")
    public Results searchByPerson(@PathVariable Integer personId) {
        return movieDao.moviesByPersonId(personId);
    }

    @GetMapping(value = "genreId")
    public Results searchByGenre(@PathVariable Integer genreId) {
        return movieDao.moviesByGenreId(genreId);
    }

    @GetMapping(value = "langId")
    public Results searchByLanguage(@PathVariable Integer langId) {
        return movieDao.moviesByLanguageId(langId);
    }

    @PostMapping(value = "year", consumes = json)
    public Results searchByYear(@RequestBody Request dateSearch) {
        return movieDao.moviesByYear(dateSearch);
    }

}
