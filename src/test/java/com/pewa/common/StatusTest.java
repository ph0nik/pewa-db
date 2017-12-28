package com.pewa.common;

import com.pewa.config.ConfigFactory;
import com.pewa.movie.Movie;
import com.pewa.status.Status;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by phonik on 2017-05-27.
 */

class StatusTest {

    private Movie movie;
    private Status movieStatus;
    List<Movie> movies;

    @BeforeEach
    private void init() {
        movie = new Movie();
        movieStatus = new Status();
        movies = Arrays.asList(movie);
    }

    @Test
    public void createExampleElement() {
        SubnodeConfiguration iniSection = ConfigFactory.getIniSection("init-sql-tables");
        iniSection.getKeys().forEachRemaining(System.out::println);

    }


}