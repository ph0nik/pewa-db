package com.pewa.movie;

import com.pewa.common.SingleSearchResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class GetImdbIdTest {
    @Disabled
    @Test
    public void tryMapOfItemsMovie() {
        MovieSearchImpl searchMovie = new MovieSearchImpl();
        Set<SingleSearchResult> result = searchMovie.externalMovieSearch("versus");
        assertNotNull(result);
        result.forEach(System.out::println);
    }
    @Disabled
    @Test
    public void tryMapOfItemsTv() {
        MovieSearchImpl searchTv = new MovieSearchImpl();
        Set<SingleSearchResult> result = searchTv.externalMovieSearch("buffy");
        result.forEach(System.out::println);
        assertNotNull(result);
    }
}
