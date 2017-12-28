package com.pewa.controller;

import com.pewa.InitAllTables;
import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.movie.Movie;
import com.pewa.movie.MovieDAO;
import com.pewa.movie.MovieDAOImpl;
import com.pewa.movie.MovieSearch;
import com.pewa.request.StatusRequest;
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
public class MovieController {

    @Autowired
    @Qualifier(value = "movieSearchTheMovieDatabase")
    private MovieSearch movieSearch;

    @Autowired
    private MovieDAO movieDao;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private InitAllTables initAllTables;

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
        return movieDao
                .moviesByTitle(query, new Results())
                .setReturnMessage();
    }

    @GetMapping(value = "id/{id}")
    public Results searchById(@PathVariable Integer id) {
        return movieDao
                .moviesById(id, new Results())
                .setReturnMessage();
    }


    @PostMapping(value = "add", consumes = json)
    public Results addMovie(@RequestBody StatusRequest request) {
        results = new Results();
        status = new Status();
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else if (!request.checkRequiredParameters().isEmpty()) {
            String returnMessage = missingParameters + request.checkRequiredParameters().toString();
            return results.setReturnMessage(returnMessage);
        } else {
            status.setElementType(request.getElementType());
            status.setEncounterId(request.getEncounterId());
            status.setComment(request.getComment());
            status.setEncounterRating(request.getEncounterRating());
            status.setEncounterDate(request.getEncounterDate());
            status.setMediaSource(request.getMediaSource());
            movie = movieParse.getItem(request.getEncounterId());
            results = movieDao.addMovie(movie, new Results());
            return statusDAO.addStatus(status, results);
        }

    }

    @GetMapping(value = "update/{id}")
    public Results updateMovie(@PathVariable Integer id) {
        movie = movieParse.getItem(id);
        results = movieDao.updMovie(movie, new Results());
        return results;
    }

    @GetMapping(value = "delete/{id}")
    public Results deleteMovie(@PathVariable Integer id) {
        results = movieDao.delMovie(id, new Results());
        return initAllTables.cleanAll(results);
    }

//    @PostMapping(value = "addstatus", consumes = json)
//    public Results addStatus(@RequestBody Request request) {
//        System.out.println(request);
//        results = new Results();
//        if (request.getStatus() == null) {
//            return results.setReturnMessage(emptyStatus);
//        } else if (!request.getStatus().checkRequiredParameters(movieType).isEmpty()) {
//            String returnMessage = missingParameters + request.getStatus().checkRequiredParameters(movieType).toString();
//            return results.setReturnMessage(returnMessage);
//        } else {
//            request.getStatus().setElementType(movieType);
//            return statusDAO.addStatus(request.getStatus(), results);
//        }
//    }

//    @PostMapping(value = "updatestatus", consumes = json)
//    public Results updateStatus(@RequestBody Request request) {
//        System.out.println(request);
//        results = new Results();
//        if (request.getStatus() == null) {
//            return results.setReturnMessage(emptyStatus);
//        } else if (!request.getStatus().checkRequiredParameters(movieType).isEmpty()) {
//            String returnMessage = missingParameters + request.getStatus().checkRequiredParameters(movieType).toString();
//            return results.setReturnMessage(returnMessage);
//        } else {
//
//            request.getStatus().setElementType(movieType);
//            return statusDAO.updateStatus(request.getStatus(), results);
//        }
//    }


//    @GetMapping(value = "delstatus/{statusId}")
//    public Results deleteStatus(@PathVariable Integer statusId) {
//        statusDAO.deleteStatus(statusId, new Results());
//        return initAllTables.cleanAll(results);
//    }

    @GetMapping(value = "personId")
    public Results searchByPerson(@PathVariable Integer personId) {
        return movieDao
                .moviesByPersonId(personId, new Results())
                .setReturnMessage();
    }

    @GetMapping(value = "genreId")
    public Results searchByGenre(@PathVariable Integer genreId) {
        return movieDao
                .moviesByGenreId(genreId, new Results())
                .setReturnMessage();
    }

    @GetMapping(value = "langId")
    public Results searchByLanguage(@PathVariable Integer langId) {
        return movieDao
                .moviesByLanguageId(langId, new Results())
                .setReturnMessage();
    }

    @PostMapping(value = "year", consumes = json)
    public Results searchByYear(@RequestBody Request dateSearch) {
        return movieDao
                .moviesByYear(dateSearch, new Results())
                .setReturnMessage();
    }

}
