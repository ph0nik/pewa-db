package com.pewa.movie;

import com.pewa.InitAllTables;
import com.pewa.PewaType;
import com.pewa.common.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.*;

/*
* Komponent był dwukrotnie rejestrowany jako bean, gdy za konfigurację servleta odpowiadał plik xml,
* a za konfigurację ogólną klasa z adnotacją @Configuration. Dodałem więc dodatkowy identyfikator dla komponentu,
* tak by nazwa drugiego rejestrowanego beana była inna. Ale nie jest to rozwiązanie problemu, bo wciąż pozozstają
* dwa więzy tej samej klasy.
*
* */
@Component/*(value = "movieDao")*/
public class MovieDAOImpl extends AbstractMediaDAO implements MovieDAO {

    private static final Logger log = LogManager.getLogger(MovieDAO.class);
    private static final String formatterString = "uuuu-MM-dd";

//    private List<InternalMediaResult> output;
    private String infoField = "";

    public MovieDAOImpl() {
        super(PewaType.MOVIE);
        tableManagement = new InitAllTables(PewaType.BOOK);
    }

    /**
     *
     *
     * */
    @Override
    public Results addMovie(Movie movieinfo) {
        infoField = movieinfo.getTitle();
        mapperList = Arrays.asList(
                "movie-mapper.insertMovie",
                "movie-mapper.insertGenreMovie",
                "movie-mapper.insertGenreBridgeMovie",
                "movie-mapper.insertPersonMovie",
                "movie-mapper.insertPersonMovieBridge",
                "movie-mapper.insertCountry",
                "movie-mapper.insertCountryMovieBridge",
                "movie-mapper.insertLanguage",
                "movie-mapper.insertLangMovieBridge"
        );
        return add(movieinfo);
    }

    @Override
    public Results updateMovie(Movie movie) {
        infoField = movie.getTitle();
        mapperList = Arrays.asList("movie-mapper.updateMovie");
        return update(movie);
    }

    @Override
    public Results deleteMovie(Integer movieid) {
        mapperList = Arrays.asList("movie-mapper.deleteMovie");
        Results delete = delete(movieid);
        return getTablesManagement().cleanAll(delete);
    }

    @Override
    public Results moviesByTitle(String search) {
        mapperList = Arrays.asList("movie-mapper.byTitleMovie");
        return search(search);
    }

    @Override
    public Results moviesById(Integer dbId) {
        mapperList = Arrays.asList("movie-mapper.byIdMovie");
        return get(dbId);
    }

    @Override
    public Results moviesByPersonId(Integer person) {
        mapperList = Arrays.asList("movie-mapper.byPersonMovie");
        return get(person);
    }

    @Override
    public Results moviesByGenreId(Integer genre) {
        mapperList = Arrays.asList("movie-mapper.byGenreMovie");
        return get(genre);
    }

    @Override
    public Results moviesByLanguageId(Integer lang) {
        mapperList = Arrays.asList("movie-mapper.byLanguageMovie");
        return get(lang);
    }

    @Override
    public Results moviesByYear(Request date) {
        Integer year = date.getYear();
        mapperList = Arrays.asList("movie-mapper.byYearMovie");
        return get(year);
    }


    @Override
    public String getInfoField() {
        return infoField;
    }
}
