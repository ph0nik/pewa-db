package com.pewa.movie;

import java.util.Set;

public interface MovieDAO {

    Boolean addMovie(Movie movieInfo);
    Set<Movie> moviesByTitle(String query);
    Set<Movie> moviesByPerson(String query);
    Set<Movie> moviesByGenre(String query);
    Set<Movie> moviesByLanguage(String query);
    Set<Movie> moviesByYear(int x, int y);

}
