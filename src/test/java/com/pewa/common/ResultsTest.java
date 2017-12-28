package com.pewa.common;

import com.pewa.movie.Movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by phonik on 2017-05-27.
 */
class ResultsTest {

    private Results results;

    private Movie movie;

    @BeforeEach
    private void initResultTestObjects() {
        results = new Results();
        movie = new Movie();
        movie.setTitlePl("Pan Kleks w kosmosie");
    }

    @Test
    public void addMovieTest() {
        results.setEncounters(movie);
        System.out.println(results.getEncounters());
    }

}