package com.pewa.movie;

import com.pewa.common.Genre;
import com.pewa.common.Language;
import com.pewa.common.Person;
import com.pewa.common.Results;

public interface MovieDAO {

    void addMovie(Movie movie);

    void updMovie(Movie movie);

    Results moviesByTitle(String query, Results results);

    Movie moviesById(String imdbId);

    /**
     * person -> person.getId()
     * */
    Results moviesByPersonId(Person person, Results results);

    /**
     *
     * genre -> genre.getId()
    * */
    Results moviesByGenreId(Genre genre, Results results);

    Results moviesByLanguageId(Language lang, Results results);

    Results moviesByYear(Integer x, Integer y, Results results);

    Results moviesByDate(String x, String y, Results results);

}
