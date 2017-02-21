package com.pewa;

import com.pewa.config.ConfigReader;
import com.pewa.movie.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseMovie {

    public static void userConnection(Movie movieInfo) {
        String dbName = ConfigReader.dbName;
        String login = ConfigReader.userName;
        String pass = ConfigReader.userPass;
        String dataBaseUrl = ConfigReader.dbUrlUser.concat(dbName);
        int recordsFound = 0;

        try (Connection conn = DriverManager.getConnection(dataBaseUrl, login, pass)) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = checkIfPresent(conn, movieInfo);
                 ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    recordsFound = rs.getRow();
                }
            }
            if (recordsFound == 0) {
                try (PreparedStatement ps = addMovie(conn, movieInfo)) {
                    ps.executeUpdate();
                }
                try (PreparedStatement ps = addGenre(conn, movieInfo)) {
                    ps.executeBatch();
                }
                try (PreparedStatement ps = addMovieGenre(conn, movieInfo)) {
                    ps.executeBatch();
                }
                try (PreparedStatement ps = addPerson(conn, movieInfo)) {
                    ps.executeBatch();
                }
                try (PreparedStatement ps = addMovieDirector(conn, movieInfo)) {
                    ps.executeBatch();
                }
                try (PreparedStatement ps = addMovieWriter(conn, movieInfo)) {
                    ps.executeBatch();
                }
                try (PreparedStatement ps = addMovieActors(conn, movieInfo)) {
                    ps.executeBatch();
                }
                try (PreparedStatement ps = addLanguage(conn, movieInfo)) {
                    ps.executeBatch();
                }
                try (PreparedStatement ps = addMovieLanguage(conn, movieInfo)) {
                    ps.executeBatch();
                }
                try (PreparedStatement ps = addCountry(conn, movieInfo)) {
                    ps.executeBatch();
                }
                try (PreparedStatement ps = addMovieCountry(conn, movieInfo)) {
                    ps.executeBatch();
                }
            } else {

            }
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static PreparedStatement checkIfPresent(Connection conn, Movie movieInfo) throws SQLException {
        String query = "SELECT * FROM movie WHERE imdb_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, movieInfo.getImdbID());
        return ps;
    }
    private static PreparedStatement addMovie(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie (title, year, rating, release_date, runtime, plot, awards, poster, metascore, imdb_rating, imdb_votes, imdb_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, movieInfo.getTitle());
        ps.setInt(2, movieInfo.getYear());
        ps.setString(3, movieInfo.getAgeRating());
        ps.setString(4, movieInfo.getRelDate());
        ps.setString(5, movieInfo.getRuntime());
        ps.setString(6, movieInfo.getPlot());
        ps.setString(7, movieInfo.getAwards());
        ps.setString(8, movieInfo.getPoster());
        ps.setString(9, movieInfo.getMetascore());
        ps.setString(10, movieInfo.getImdbRating());
        ps.setString(11, movieInfo.getImdbVotes());
        ps.setString(12, movieInfo.getImdbID());
        return ps;
    }
    private static PreparedStatement addPerson(Connection conn, Movie movieInfo) throws SQLException {
        List<String> tempListOfPeople = new ArrayList(movieInfo.getDirector());
        tempListOfPeople.addAll(movieInfo.getWriter());
        tempListOfPeople.addAll(movieInfo.getActors());
        tempListOfPeople = tempListOfPeople.stream().distinct().collect(Collectors.toList());
        String query = "INSERT INTO movie_person (name) SELECT ? WHERE NOT EXISTS(SELECT * FROM movie_person WHERE name=?)";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String person : tempListOfPeople) {
            ps.setString(1, person);
            ps.setString(2, person);
            ps.addBatch();
        }
        return ps;
    }
    private static PreparedStatement addMovieDirector(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_person_combine (person_id, movie_id, job) SELECT movie_person.id, movie.id, 'director' FROM  movie_person, movie WHERE name=? AND imdb_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String person : movieInfo.getDirector()) {
            ps.setString(1, person);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }
    private static PreparedStatement addMovieWriter(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_person_combine (person_id, movie_id, job) SELECT movie_person.id, movie.id, 'writer' FROM  movie_person, movie WHERE name=? AND imdb_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String person : movieInfo.getWriter()) {
            ps.setString(1, person);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }
    private static PreparedStatement addMovieActors(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_person_combine (person_id, movie_id, job) SELECT movie_person.id, movie.id, 'actor' FROM  movie_person, movie WHERE name=? AND imdb_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String person : movieInfo.getActors()) {
            ps.setString(1, person);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }
    private static PreparedStatement addLanguage(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_language (language) SELECT ? WHERE NOT EXISTS(SELECT * FROM movie_language WHERE language=?)";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String language : movieInfo.getLanguage()) {
            ps.setString(1, language);
            ps.setString(2, language);
            ps.addBatch();
        }
        return ps;
    }
    private static PreparedStatement addMovieLanguage(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_language_combined (language_id, movie_id) SELECT movie_language.id, movie.id FROM  movie_language, movie WHERE language=? AND imdb_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String language : movieInfo.getLanguage()) {
            ps.setString(1, language);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }
    private static PreparedStatement addCountry(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_country (country) SELECT ? WHERE NOT EXISTS(SELECT * FROM movie_country WHERE country=?)";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String country : movieInfo.getCountry()) {
            ps.setString(1, country);
            ps.setString(2, country);
            ps.addBatch();
        }
        return ps;
    }
    private static PreparedStatement addMovieCountry(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_country_combined (country_id, movie_id) SELECT movie_country.id, movie.id FROM  movie_country, movie WHERE country=? AND imdb_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String country : movieInfo.getCountry()) {
            ps.setString(1, country);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }
    private static PreparedStatement addGenre(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_genre (genre) SELECT ? WHERE NOT EXISTS(SELECT * FROM movie_genre WHERE genre=?)";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String genre : movieInfo.getGenre()) {
            ps.setString(1, genre);
            ps.setString(2, genre);
            ps.addBatch();
        }
        return ps;
    }
    private static PreparedStatement addMovieGenre(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_genre_combine (genre_id, movie_id) SELECT movie_genre.id, movie.id FROM  movie_genre, movie  where movie_genre.genre=? and movie.imdb_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String genre : movieInfo.getGenre()) {
            ps.setString(1, genre);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }

}
