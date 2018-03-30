package com.pewa.movie;

import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
* Komponent był dwukrotnie rejestrowany jako bean, gdy za konfigurację servleta odpowiadał plik xml,
* a za konfigurację ogólną klasa z adnotacją @Configuration. Dodałem więc dodatkowy identyfikator dla komponentu,
* tak by nazwa drugiego rejestrowanego beana była inna. Ale nie jest to rozwiązanie problemu, bo wciąż pozozstają
* dwa więzy tej samej klasy.
*
* */
@Component/*(value = "movieDao")*/
public class MovieDAOImpl extends MediaDAO implements MovieDAO {

    private static final Logger log = LogManager.getLogger(MovieDAO.class);

    private static final String formatterString = "uuuu-MM-dd";

    private List<EncounterElement> output;
    private List<String> mapperList = new ArrayList<>();
    private String infoField = "";

    public MovieDAOImpl() {
        super(PewaType.MOVIE);
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
        return super.add(movieinfo);
    }

    @Override
    public Results updateMovie(Movie movie) {
        infoField = movie.getTitle();
        mapperList = Arrays.asList("movie-mapper.updateMovie");
        return super.update(movie);
    }

    @Override
    public Results deleteMovie(Integer movieid) {
        mapperList = Arrays.asList("movie-mapper.deleteMovie");
        return super.delete(movieid);
    }

    @Override
    public Results moviesByTitle(String search) {
        mapperList = Arrays.asList("movie-mapper.byTitleMovie");
        return super.search(search);
    }

    @Override
    public Results moviesById(Integer dbId) {
        mapperList = Arrays.asList("movie-mapper.byIdMovie");
        return super.get(dbId);
    }

    @Override
    public Results moviesByPersonId(Integer person) {
        mapperList = Arrays.asList("movie-mapper.byPersonMovie");
        return super.get(person);
    }

    @Override
    public Results moviesByGenreId(Integer genre) {
        mapperList = Arrays.asList("movie-mapper.byGenreMovie");
        return super.get(genre);
    }

    @Override
    public Results moviesByLanguageId(Integer lang) {
        mapperList = Arrays.asList("movie-mapper.byLanguageMovie");
        return super.get(lang);
    }

    @Override
    public Results moviesByYear(Request date) {
        Integer year = date.getYear();
        mapperList = Arrays.asList("movie-mapper.byYearMovie");
        return super.get(year);
    }

    @Override
    public Results moviesByDate(String x, String y) {
        return null;
//        Results results = new Results();
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterString);
//            LocalDate start, end;
//            start = (x.isEmpty()) ? LocalDate.now() : LocalDate.parse(x, formatter);
//            end = (y.isEmpty()) ? LocalDate.now() : LocalDate.parse(y, formatter);
//            Map<String, LocalDate> map = new HashMap<>();
//            if (start.compareTo(end) >= 0) {
//                map.put("start", end);
//                map.put("end", start);
//            } else {
//                map.put("start", start);
//                map.put("end", end);
//            }
//            output = session.selectList(ConfigFactory.get("movie-mapper.byYearMovie"), map);
//            session.commit();
//        }
//        for (Encounter element : output) {
//            results.setEncounters(element);
//        }
//        return results;
    }

    @Override
    public List<String> getMapperList() {
        return mapperList;
    }

    @Override
    public String getInfoField() {
        return infoField;
    }
}
