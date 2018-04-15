package com.pewa.movie;

import com.pewa.MediaParse;
import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.common.ExternalMediaResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by phonik on 2017-10-18.
 */
class MovieSearchTheMovieDatabaseTest {

    private Request request;

    @BeforeEach
    private void config() {
        request = new Request();
    }

    @Disabled
    @Test
    public void tryMapOfItemsMovie() {
        MovieSearchTheMovieDatabase searchMovie = new MovieSearchTheMovieDatabase();
        String query = "terminator";
        Results result = searchMovie.externalMovieSearch(query, new Results());
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Disabled
    @Test
    public void tryEmptyResultsCollection() {
        Set<ExternalMediaResult> searchResultSet = new TreeSet<>();
        Results results = new Results();
        searchResultSet.forEach(results::setEncounters);
        System.out.println(results);
    }

    // aliens - 679
    // terminator - 218
    @Disabled
    @Test
    public void getMovieDetails() {
        MediaParse<Movie, Integer> getMovie = new MovieParserTmdb();
        Request request = new Request();
        Integer movieid = 400710;
        Movie movie = getMovie.getItem(movieid); // Aliens
        System.out.println(movie);
    }
}