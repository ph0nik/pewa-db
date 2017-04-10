package com.pewa.movie;

import com.pewa.MediaParse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class MovieSearchTest {
    // SingleSearchResult{url='tt0115530', desc='American Buffalo (1996) reż. Michael Corrente'}

    @Disabled
    @Test
    public void parseSelectedPositionMovie() {
        MediaParse<Movie, String> movieParser = new MovieParser();
        Movie americanBuffalo = movieParser.getItem("tt0115530");
        System.out.println(americanBuffalo.toString());
        MovieDAO dbm = new MovieDAOImpl();
        dbm.addMovie(americanBuffalo);
    }

    @Disabled
    @Test
    public void parseErrorCheck() {
        MediaParse<Movie, String> movieParser = new MovieParser();
        Movie americanBuffalo = movieParser.getItem("asdasda");

        System.out.println(americanBuffalo.isEmpty());

    }
}
