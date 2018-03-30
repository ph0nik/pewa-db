package com.pewa.movie;

import com.pewa.common.*;

public interface MovieDAO {

    Results addMovie(Movie movie);

    Results updateMovie(Movie movie);

    Results deleteMovie(Integer movieid);

    Results moviesByTitle(String query);

    Results moviesById(Integer dbId);

    Results moviesByPersonId(Integer person);

    Results moviesByGenreId(Integer genre);

    Results moviesByLanguageId(Integer lang);

    Results moviesByYear(Request date);

    Results moviesByDate(String x, String y);

}
