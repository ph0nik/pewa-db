package com.pewa.movie;

import com.pewa.MediaParse;
import com.pewa.common.Results;
import com.pewa.common.SingleSearchResult;
import com.pewa.util.GlobalSearch;
import jdk.nashorn.internal.objects.Global;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;


public class MovieSearchTest {
    // SingleSearchResult{url='tt0115530', desc='American Buffalo (1996) reż. Michael Corrente'}

    private Results results;

    @BeforeEach
    private void initTest() {
        results = new Results();
    }


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

    @Disabled
    @Test
    public void searchAll() {
        GlobalSearch globalSearch = new GlobalSearch();
        String query = "alien";
        Results results = globalSearch.itemsInternalByTitle(query);
        System.out.println(results);
    }

    @Disabled
    @Test
    public void searchAllExternal() {
        GlobalSearch globalSearch = new GlobalSearch();
        String query = "alien";
        Results results = globalSearch.itemsExternalByTitle(query, new Results());
        results.getEncounters().forEach(System.out::println);
    }
}
