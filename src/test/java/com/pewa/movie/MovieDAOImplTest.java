package com.pewa.movie;

import com.pewa.MediaModel;
import com.pewa.MediaParse;
import com.pewa.MediaSource;
import com.pewa.PewaType;
import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.status.Status;
import com.pewa.status.StatusDAO;
import com.pewa.status.StatusDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;


/**
 * Created by phonik on 2017-04-04.
 */
public class MovieDAOImplTest {

    private Results results;
    private MovieDAO movieDAO;
    private StatusDAO statusDAO;
    private Request request;

    @BeforeEach
    public void setObjects() {
        results = new Results();
        movieDAO = new MovieDAOImpl();
        statusDAO = new StatusDAOImpl();
        request = new Request();
    }

    @Disabled
    @Test
    public void getMovieFromImdb() throws IOException {
        MovieParser movieParser = new MovieParser();
        //tt0095647
        Movie americanBuffalo = movieParser.getItem("tt0092106");
        System.out.println(americanBuffalo.toString());

    }


    // aliens - 679
    // terminator - 218
    @Disabled
    @Test
    public void addMovie() {
        MediaParse<Movie, Integer> movieParser = new MovieParserTmdb();
        Movie movie = movieParser.getItem(399035);
        System.out.println(movie.toString());
        Results results = movieDAO.addMovie(movie);
        System.out.println(results);
//        movieDAO.addMovie(movie, results);
//        Status status = new Status();
//        status.setElementType(PewaType.MOVIE);
//        status.setComment("Aliens");
//        status.setEncounterDate(LocalDate.now());
//        status.setMediaSource(MediaSource.COMPUTER);
//        status.setEncounterRating(9);
//        status.setEncounterId(movie.getImdbID());
//        statusDAO.addStatus(status, results);

    }

    @Disabled
    @Test
    public void deleteMovieFromDb() {
        movieDAO.deleteMovie(96);
    }


    @Disabled
    @Test
    public void searchMovieByQuery(){
        String req = "blade";
        Results movie = movieDAO.moviesByTitle(req);
        System.out.println(movie);
    }
    @Disabled
    @Test
    public void updateMovie(){
        MediaParse<Movie, Integer> movieParser = new MovieParserTmdb();
        Integer request = 679;
        Movie americanBuffalo = movieParser.getItem(request);
//        System.out.println(americanBuffalo.toString());
        Results results = movieDAO.updateMovie(americanBuffalo);
        System.out.println(results);
    }
    @Disabled
    @Test
    public void getMovieById(){
        Integer request = 6;
        Results sampleMovie = movieDAO.moviesById(request);
        for (MediaModel m : sampleMovie) {
            System.out.println(((Movie) m).getCountry());
        }

    }

    @Disabled
    @Test
    public void searchMovieByPerson() {
        Integer person = 4;
        Results moviesByPerson = movieDAO.moviesByPersonId(person);
        System.out.println(results);
        person = 2;
        moviesByPerson = movieDAO.moviesByPersonId(person);
        System.out.println(results);


    }
    @Disabled
    @Test
    public void searchMovieByGenre(){
        Integer genre = 3;
        Results moviesByGenre = movieDAO.moviesByGenreId(genre);
        System.out.println(results);
    }

    @Disabled
    @Test
    public void searchMovieByYear() {
        Request req = new Request();
        req.setDateInString(LocalDate.of(1999,1,1).toString());
        req.setDateOutString(LocalDate.of(2001,1,1).toString());
        Results moviesByYear = movieDAO.moviesByYear(req);
        System.out.println(results);
    }

    @Disabled
    @Test
    public void getMoviesByEncountersNumber() {
        Results sampleList = statusDAO.getStatusByNumber(5, results);
        System.out.println(sampleList);
    }

    @Disabled
    @Test
    public void insertStatusAndBridge() {
        Status status = new Status();
        status.setElementType(PewaType.MOVIE);
        status.setComment("Bardzo fajny film");
        status.setEncounterDate(LocalDate.now());
        status.setMediaSource(MediaSource.COMPUTER);
        status.setEncounterRating(10);
        status.setEncounterId(110092106);
        statusDAO.addStatus(status);
    }

    @Disabled
    @Test
    public void isMovieEmpty() {
        Movie movie = new Movie();
        System.out.println(movie);
        System.out.println(movie.isEmpty());
    }

    @Disabled
    @Test
    public void setOfStatuses() {
        Status status = new Status();
        status.setMediaSource(MediaSource.COMPUTER);
        status.setEncounterDate(LocalDate.of(2017,5,2));
        status.setEncounterId(1);
        status.setEncounterRating(6);
        status.setComment("evil aliens");

        Status status1 = new Status();
        status1.setMediaSource(MediaSource.COMPUTER);
        status1.setEncounterDate(LocalDate.of(2017,5,2));
        status1.setEncounterId(4);
        status1.setEncounterRating(6);
        status1.setComment("evil aliens");

        Set<Status> dodawanie = new TreeSet<>();
        dodawanie.add(status);
        System.out.println("pierwszy el " + dodawanie);
        dodawanie.add(status1);
        System.out.println("drugi el " + dodawanie);

//Status{elementType=null, mediaSource=COMPUTER, addedDate=2017-06-02, encounterDate=2017-05-02, encounterId=null, id=4, rating=7, comment='evil aliens - druga recenzja'}]
    }

}