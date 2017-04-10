package com.pewa.movie;

import com.pewa.MediaParse;
import com.pewa.common.Genre;
import com.pewa.common.Person;
import com.pewa.common.Results;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * Created by phonik on 2017-04-04.
 */
public class MovieDAOImplTest {

    private Results results;
    private MovieDAO movieDAO;

    @BeforeEach
    public void setObjects() {
        results = new Results();
        movieDAO = new MovieDAOImpl();
    }

    @Disabled
    @Test
    public void addMovie() {
        MediaParse<Movie, String> movieParser = new MovieParser();
        Movie americanBuffalo = movieParser.getItem("tt0115530");
        System.out.println(americanBuffalo.toString());
        movieDAO.addMovie(americanBuffalo);
    }
    @Disabled
    @Test
    public void searchMovieByQuery(){
        Results movie = movieDAO.moviesByTitle("buff", results);
        System.out.println(movie);
    }
    @Disabled
    @Test
    public void updateMovie(){
        MediaParse<Movie, String> movieParser = new MovieParser();
        Movie americanBuffalo = movieParser.getItem("tt0115530");
        System.out.println(americanBuffalo.toString());
        movieDAO.updMovie(americanBuffalo);
    }
    @Disabled
    @Test
    public void getMovieById(){
        Movie americaBuffalo = movieDAO.moviesById("tt0115530");
        americaBuffalo.getStaff().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void searchMovieByPerson() {
        Person person = new Person();
        person.setId(27); // David mamet
        Results moviesByPerson = movieDAO.moviesByPersonId(person, results);
        System.out.println(results);


    }
    @Disabled
    @Test
    public void searchMovieByGenre(){
        Genre genre = new Genre();
        genre.setId(3);
        Results moviesByGenre = movieDAO.moviesByGenreId(genre, results);
        System.out.println(results);
    }

    @Disabled
    @Test
    public void searchMovieByYear() {
        Integer dateIn = 1980;
        Integer dateOut = 1990;
        Results moviesByYear = movieDAO.moviesByYear(dateIn, dateOut, results);
        System.out.println(results);
    }
}