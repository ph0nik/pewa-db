package com.pewa.movie;

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
public class MovieDAOImpl implements MovieDAO {

    private static final Logger log = LogManager.getLogger(MovieDAO.class);
    private static final List<String> insertMethods = Arrays.asList(
            ConfigFactory.get("movie-mapper.insertMovie")
            , ConfigFactory.get("movie-mapper.insertGenreMovie")
            , ConfigFactory.get("movie-mapper.insertGenreBridgeMovie")
            , ConfigFactory.get("movie-mapper.insertPersonMovie")
            , ConfigFactory.get("movie-mapper.insertPersonMovieBridge")
            , ConfigFactory.get("movie-mapper.insertCountry")
            , ConfigFactory.get("movie-mapper.insertCountryMovieBridge")
            , ConfigFactory.get("movie-mapper.insertLanguage")
            , ConfigFactory.get("movie-mapper.insertLangMovieBridge"));
    private static final String formatterString = "uuuu-MM-dd";
    private static final String addSuccess = "Movie item added  : ";
    private static final String updateSuccess = "Movie item updated : ";
    private static final String deleteSuccess = "Movie item deleted : ";
    private static final String nothingFound = "No movie with this Id found : ";
    private static final String alreadyInDb = "Movie item already in database : ";
    private List<EncounterElement> output;
    private StringBuilder logMessage;
    private Integer rowsAffected;

    @Override
    public Results addMovie(Movie movieinfo, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            for (String method : insertMethods) {
                logMessage = new StringBuilder(LocalDate.now() + this.getClass().toString() + ": " + method);
                log.debug(logMessage);
                rowsAffected += session.insert(method, movieinfo);
            }
            session.commit();
            results.setRowsAffected(rowsAffected);
            if (rowsAffected != 0)
                results.setReturnMessage(addSuccess + movieinfo.getTitle() + " " + movieinfo.getYear());
            else results.setReturnMessage(alreadyInDb + movieinfo.getTitle() + " " + movieinfo.getYear());
        }
        return results;
    }

    @Override
    public Results updMovie(Movie movie, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.update(ConfigFactory.get("movie-mapper.updateMovie"), movie);
            session.commit();
        }
        results.setRowsAffected(rowsAffected);
        if (rowsAffected != 0) results.setReturnMessage(updateSuccess + movie.getTitle() + " " + movie.getYear());
        else results.setReturnMessage(nothingFound + movie.getTitle() + " " + movie.getYear());
        return results;
    }

    @Override
    public Results delMovie(Integer movieid, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.delete(ConfigFactory.get("movie-mapper.deleteMovie"), movieid);
            session.commit();
            results.setRowsAffected(rowsAffected);
        }
        if (rowsAffected != 0) results.setReturnMessage(deleteSuccess + movieid);
        else results.setReturnMessage(nothingFound + movieid);
        return results;
    }

    @Override
    public Results moviesByTitle(String search, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            String query = new StringBuilder("%")
                    .append(search)
                    .append("%")
                    .toString();
            output = session.selectList(ConfigFactory.get("movie-mapper.byTitleMovie"), query);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results moviesById(Integer dbId, Results results) {
        Movie movie;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            movie = session.selectOne(ConfigFactory.get("movie-mapper.byIdMovie"), dbId);
            session.commit();
            movie.getStatus().forEach(x -> x.setEncounterId(movie.getTmdbId()));
            results.setEncounters(movie);
        } catch (NullPointerException npe) {
            log.error(npe.getStackTrace());
        }
        return results;
    }

    @Override
    public Results moviesByPersonId(Integer person, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("movie-mapper.byPersonMovie"), person);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results moviesByGenreId(Integer genre, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("movie-mapper.byGenreMovie"), genre);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results moviesByLanguageId(Integer lang, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("movie-mapper.byLanguageMovie"), lang);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results moviesByYear(Request date, Results results) {
        Integer year = date.getYear();
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("movie-mapper.byYearMovie"), year);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;

    }

    @Override
    public Results moviesByDate(String x, String y, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterString);
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
            output = session.selectList(ConfigFactory.get("movie-mapper.byYearMovie"), map);
            session.commit();
        }
        for (Encounter element : output) {
            results.setEncounters(element);
        }
        return results;
    }
}
