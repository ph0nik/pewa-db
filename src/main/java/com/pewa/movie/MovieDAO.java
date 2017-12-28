package com.pewa.movie;

import com.pewa.common.*;
import com.pewa.movie.tmdb.Result;

public interface MovieDAO {

    Results addMovie(Movie movie, Results results);

    Results updMovie(Movie movie, Results results);

    Results delMovie(Integer movieid, Results results);

    Results moviesByTitle(String query, Results results);

    Results moviesById(Integer dbId, Results results);

    Results moviesByPersonId(Integer person, Results results);

    Results moviesByGenreId(Integer genre, Results results);

    Results moviesByLanguageId(Integer lang, Results results);

    Results moviesByYear(Request date, Results results);

    Results moviesByDate(String x, String y, Results results);

}
