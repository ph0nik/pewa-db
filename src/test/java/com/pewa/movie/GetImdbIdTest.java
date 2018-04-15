package com.pewa.movie;

import com.pewa.common.Request;
import com.pewa.common.Results;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class GetImdbIdTest {

    private Results results;
    private Request request;

    @BeforeEach
    private void initTest() {
        results = new Results();
        request = new Request();
    }

    @Disabled
    @Test
    public void tryMapOfItemsMovie() {
        MovieSearch searchMovie = new MovieSearchTheMovieDatabase();
        String query = "aliens";
        Results result = searchMovie.externalMovieSearch(query, new Results());
        assertNotNull(result);
//        result.forEach(System.out::println);
    }
}
