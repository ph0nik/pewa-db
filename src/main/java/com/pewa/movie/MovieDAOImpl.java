package com.pewa.movie;

import com.pewa.common.Genre;
import com.pewa.common.Language;
import com.pewa.dao.MyBatisFactory;
import com.pewa.common.Person;
import com.pewa.common.Results;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by phonik on 2017-04-04.
 */
/*
* Komponent był dwukrotnie rejestrowany jako bean, gdy za konfigurację servleta odpowiadał plik xml,
* a za konfigurację ogólną klasa z adnotacją @Configuration. Dodałem więc dodatkowy identyfikator dla komponentu,
* tak by nazwa drugiego rejestrowanego beana była inna. Ale nie jest to rozwiązanie problemu, bo wciąż pozozstają
* dwa więzy tej samej klasy.
*
* */
@Component/*(value = "movieDao")*/
public class MovieDAOImpl implements MovieDAO {

    private static final Logger log = LogManager.getLogger(MovieDAO.class);
    private List<Movie> output;

    @Override
    public void addMovie(Movie movieinfo) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.BATCH, false)) {
            session.insert("insertMovie", movieinfo);
            session.insert("insertGenreMovie", movieinfo);
            session.insert("insertGenreBridgeMovie", movieinfo);
            session.insert("insertPersonMovie", movieinfo);
            session.insert("insertPersonMovieBridge", movieinfo);
            session.insert("insertCountry", movieinfo);
            session.insert("insertCountryMovieBridge", movieinfo);
            session.insert("insertLanguage", movieinfo);
            session.insert("insertLangMovieBridge", movieinfo);
            session.commit();
        }
    }

    @Override
    public void updMovie(Movie movie) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.BATCH, false)) {
            session.update("updateMovie", movie);
            session.commit();
        }
    }

    @Override
    public Results moviesByTitle(String search, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            String query = new StringBuilder("%")
                    .append(search)
                    .append("%")
                    .toString();
            output = session.selectList("byTitleMovie", query);
            session.commit();
        }
        return results.setMovies(output);
    }

    @Override
    public Movie moviesById(String imdbId) {
        Movie movie;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            movie = session.selectOne("byIdMovie", imdbId);
            session.commit();
        }
        return movie;

    }

    @Override
    public Results moviesByPersonId(Person person, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            output = session.selectList("byPersonMovie", person.getId());
            session.commit();
        }
        return results.setMovies(output);
    }

    @Override
    public Results moviesByGenreId(Genre genre, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            output = session.selectList("byGenreMovie", genre.getId());
            session.commit();
        }
        return results.setMovies(output);
    }

    @Override
    public Results moviesByLanguageId(Language lang, Results results) {
        return null;
    }

    @Override
    public Results moviesByYear(Integer x, Integer y, Results results) {

        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {

            Map<String, Integer> map = new HashMap<>();
            if (x >= y) {
                map.put("start", y);
                map.put("end", x);
            } else {
                map.put("start", x);
                map.put("end", y);
            }
            output = session.selectList("byYearMovie", map);
            session.commit();
        }
        return results.setMovies(output);
    }

    @Override
    public Results moviesByDate(String x, String y, Results results) {

        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate start, end;
            start = (x.isEmpty()) ? LocalDate.now() : LocalDate.parse(x, formatter);
            end = (y.isEmpty()) ? LocalDate.now() : LocalDate.parse(y, formatter);
            Map<String, LocalDate> map = new HashMap<>();
            if (start.compareTo(end) >= 0) {
                map.put("start", end);
                map.put("end", start);
            } else {
                map.put("start", start);
                map.put("end", end);
            }
            output = session.selectList("byYearMovie", map);
            session.commit();
        }
        return results.setMovies(output);
    }
}
