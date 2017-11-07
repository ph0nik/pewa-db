package com.pewa.controller;

import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.movie.Movie;
import com.pewa.movie.MovieDAO;
import com.pewa.movie.MovieSearch;
import com.pewa.status.Status;
import com.pewa.status.StatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.Set;

/**
 * Created by phonik on 2017-03-02.
 */

@RestController
@RequestMapping("/movie/")
public class MovieControllerImpl implements MovieController {

    @Autowired
    private MovieSearch movieSearch;

    @Autowired
    private MovieDAO movieDao;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    @Qualifier(value = "movieParser")
    private MediaParse<Movie, String> movieParse;

    private Results results;

    @GetMapping(value = "search/{query}")
    public Set<SingleSearchResult> searchExternal(@PathVariable String query) {
        return movieSearch.externalMovieSearch(query);
    }

    @GetMapping(value = "searchdb/{query}")
    public Results searchDb(@PathVariable String query) {
        return movieDao
                .moviesByTitle(query, new Results())
                .setMessage();
    }

    @GetMapping(value = "id/{query}")
    public Results searchById(@PathVariable Integer query) {
        return movieDao
                .moviesById(query, new Results())
                .setMessage();
    }

    // TODO - zwraca nullpointerexception
    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results addMovie(@RequestBody Request request) {
        Status status;
        Results results = new Results();
        Movie movie = movieParse.getItem(request.getImdbId());
        if (movie.isEmpty()) {
            results.setMessage("Error - empty element");
            return results;
        }
        else {
            results = movieDao
                    .addMovie(movie, new Results());
            status = request.setStatus(PewaType.MOVIE, movie.getImdbID());
            return statusDAO
                    .addStatusMovie(status, results);
        }

    }

    @PostMapping(value = "addstatus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results addStatus(@RequestBody Request request) {
        Status status = request.setStatus(PewaType.MOVIE, request.getId());
        return statusDAO
                .addStatusMovie(status, new Results());
    }

    @PostMapping(value = "updatestatus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results updateStatus(@RequestBody Request request) {
        Status status = request.setStatus(PewaType.MOVIE, request.getId());
        return statusDAO
                .updateStatus(status, new Results());
    }
    /*
    * zapytanie POST w body musi mieć jsona, json musi reprezentować obiekt Request
    *
    * */
    @PostMapping(value = "person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByPerson(@RequestBody Request request) {
        Person person = new Person(request.getId());
        return movieDao
                .moviesByPersonId(person, new Results())
                .setMessage();
    }

    @PostMapping(value = "genre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByGenre(@RequestBody Request request) {
        Genre genre = new Genre(request.getId());
        return movieDao
                .moviesByGenreId(genre, new Results())
                .setMessage();
    }

    @PostMapping(value = "lang", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByLanguage(@RequestBody Request request) {
        Language lang = new Language(request.getId());
        return movieDao
                .moviesByLanguageId(lang, new Results())
                .setMessage();
    }

    @PostMapping(value = "date", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByDate(@RequestBody Request dateSearch) {
        return movieDao
                .moviesByYear(
                        dateSearch.getDateIn()
                        , dateSearch.getDateOut()
                        , new Results())
                .setMessage();
    }

}
