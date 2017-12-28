package com.pewa.movie;

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

//    termit - 103064
//    john wick -  2911666
    // evil aliens = 110383353
    // aliens - 110090605
    // cowboys vs aliens - 110409847
    @Disabled
    @Test
    public void addMovie() {
        MediaParse<Movie, String> movieParser = new MovieParser();
        Movie movie = movieParser.getItem("tt0383353");
        System.out.println(movie.toString());
        movieDAO.addMovie(movie, results);
        Status status = new Status();
        status.setElementType(PewaType.MOVIE);
        status.setComment("Bardzo fajny film");
        status.setEncounterDate(LocalDate.now());
        status.setMediaSource(MediaSource.COMPUTER);
        status.setEncounterRating(9);
        status.setEncounterId(movie.getImdbID());
        statusDAO.addStatus(status, results);
    }
    @Disabled
    @Test
    public void searchMovieByQuery(){
        String req = "alien";
        Results movie = movieDAO.moviesByTitle(req, results);
        System.out.println(movie);
    }
    @Disabled
    @Test
    public void updateMovie(){
        MediaParse<Movie, Integer> movieParser = new MovieParserTmdb();
        Integer request = 218;
        Movie americanBuffalo = movieParser.getItem(request);
//        System.out.println(americanBuffalo.toString());
        Results results = movieDAO.updMovie(americanBuffalo, new Results());
        System.out.println(results);
    }
    @Disabled
    @Test
    public void getMovieById(){
        Integer request = 7;
        Results sampleMovie = movieDAO.moviesById(request, results);
        Movie movie = (Movie) sampleMovie.getEncounters().get(0);

        System.out.println(movie);
//        sampleMovie.getStaff().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void searchMovieByPerson() {
        Integer person = 4;
        Results moviesByPerson = movieDAO.moviesByPersonId(person, results);
        System.out.println(results);
        person = 2;
        moviesByPerson = movieDAO.moviesByPersonId(person, results);
        System.out.println(results);


    }
    @Disabled
    @Test
    public void searchMovieByGenre(){
        Integer genre = 3;
        Results moviesByGenre = movieDAO.moviesByGenreId(genre, results);
        System.out.println(results);
    }

    @Disabled
    @Test
    public void searchMovieByYear() {
        Request req = new Request();
        req.setDateInString(LocalDate.of(1999,1,1).toString());
        req.setDateOutString(LocalDate.of(2001,1,1).toString());
        Results moviesByYear = movieDAO.moviesByYear(req, results);
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
        statusDAO.addStatus(status, results);
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