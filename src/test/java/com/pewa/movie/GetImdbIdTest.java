package com.pewa.movie;

import com.pewa.PewaType;
import com.pewa.SingleSearchResult;
import com.pewa.config.ConfigReader;
import com.pewa.imdb.GetImdbId;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class GetImdbIdTest {

    @BeforeClass
    public static void initTest() {
        ConfigReader.load();
    }

    @Test
    public void tryMapOfItemsMovie() {
        GetImdbId searchMovie = new GetImdbId();
        Set<SingleSearchResult> result = searchMovie.externalMovieSearch("versus", PewaType.MOVIE);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    public void tryMapOfItemsTv() {
        GetImdbId searchTv = new GetImdbId();
        Set<SingleSearchResult> result = searchTv.externalMovieSearch("buffy", PewaType.TVSERIES);
        result.forEach(System.out::println);
        assertNotNull(result);
    }
}
