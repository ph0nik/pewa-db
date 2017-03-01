package com.pewa;

import com.pewa.config.ConfigReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseInit {

    private static void userConnection(String dataBaseUrl, String login, String pass, List<String> queries) {
        try (
                Connection conn = DriverManager.getConnection(dataBaseUrl, login, pass);
                Statement st = conn.createStatement()) {
            conn.setAutoCommit(true);
            for (String query : queries) {
                st.executeUpdate(query);
            }
//            conn.commit();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dbInit() {
        String urlRoot = ConfigReader.dbUrlRoot;
        String passRoot = ConfigReader.rootPass;
        String loginRoot = ConfigReader.dbLogin;
        List<String> queries = new ArrayList<>();
        String createDb = new StringBuilder("CREATE DATABASE IF NOT EXISTS ")
                .append(ConfigReader.dbName)
                .toString();
        String createUser = new StringBuilder("CREATE USER IF NOT EXISTS '")
                .append(ConfigReader.userName)
                .append("'@'localhost' identified by '")
                .append(ConfigReader.userPass)
                .append("'")
                .toString();
        String grantPriv = new StringBuffer("GRANT ALL ON ")
                .append(ConfigReader.dbName)
                .append(".* TO '")
                .append(ConfigReader.userName)
                .append("'@'localhost'")
                .toString();
        queries.addAll(Arrays.asList(createDb, createUser, grantPriv));
        userConnection(urlRoot, loginRoot, passRoot, queries);
    }

    public static void createTables() {
        String dbName = ConfigReader.dbName;
        String login = ConfigReader.userName;
        String pass = ConfigReader.userPass;
        String dataBaseUrl = ConfigReader.dbUrlUser.concat(dbName);
        List<String> queries = new ArrayList();
        String createGenre = "CREATE TABLE IF NOT EXISTS movie_genre(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, genre VARCHAR(20) UNIQUE)";
        String createPerson = "CREATE TABLE IF NOT EXISTS movie_person(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(60) UNIQUE)";
        String createCountry = "CREATE TABLE IF NOT EXISTS movie_country(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, country VARCHAR(30) UNIQUE)";
        String createLanguage = "CREATE TABLE IF NOT EXISTS movie_language(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, language VARCHAR(30) UNIQUE)";
        String createMovie = new StringBuilder("CREATE TABLE IF NOT EXISTS movie(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, ")
                .append("title VARCHAR(30), ")
                .append("year INT NOT NULL, ")
                .append("rating VARCHAR(10), ")
                .append("release_date VARCHAR(20), ")
                .append("runtime VARCHAR(10), ")
                .append("plot VARCHAR(500), ")
                .append("awards VARCHAR(100), ")
                .append("poster VARCHAR(200), ")
                .append("metascore VARCHAR(10), ")
                .append("imdb_rating VARCHAR(5), ")
                .append("imdb_votes VARCHAR(10), ")
                .append("imdb_id VARCHAR(10) UNIQUE)")
                .toString();
        String createWatched = new StringBuilder("CREATE TABLE IF NOT EXISTS watched(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, ")
                .append("movie_id INT NOT NULL, ")
                .append("date DATE, ")
                .append("source VARCHAR(30), ")
                .append("type INT NOT NULL, ")
                .append("rating INT NOT NULL, ")
                .append("FOREIGN KEY(movie_id) REFERENCES movie(id)")
                .append(")")
                .toString();
        String createMoviePerson = new StringBuilder("CREATE TABLE IF NOT EXISTS movie_person_combine(")
                .append("person_id INT NOT NULL, ")
                .append("movie_id INT NOT NULL, ")
                .append("job VARCHAR(20), ")
                .append("FOREIGN KEY(movie_id) REFERENCES movie(id) ON DELETE CASCADE, ")
                .append("FOREIGN KEY(person_id) REFERENCES movie_person(id) ON DELETE CASCADE, ")
                .append("CONSTRAINT uc_movie_person_combine UNIQUE (person_id, movie_id, job))")
                .toString();
        String createMovieGenre = new StringBuilder("CREATE TABLE IF NOT EXISTS movie_genre_combine(")
                .append("genre_id INT NOT NULL, ")
                .append("movie_id INT NOT NULL, ")
                .append("FOREIGN KEY(genre_id) REFERENCES movie_genre(id) ON DELETE CASCADE, ")
                .append("FOREIGN KEY(movie_id) REFERENCES movie(id) ON DELETE CASCADE, ")
                .append("CONSTRAINT uc_movie_genre_combine UNIQUE (genre_id, movie_id))")
                .toString();
        String createMovieLanguage = new StringBuilder("CREATE TABLE IF NOT EXISTS movie_language_combined(")
                .append("language_id INT NOT NULL, ")
                .append("movie_id INT NOT NULL, ")
                .append("FOREIGN KEY(language_id) REFERENCES movie_language(id) ON DELETE CASCADE, ")
                .append("FOREIGN KEY(movie_id) REFERENCES movie(id) ON DELETE CASCADE, ")
                .append("CONSTRAINT uc_movie_language_combined UNIQUE (language_id, movie_id))")
                .toString();
        String createMovieCountry = new StringBuilder("CREATE TABLE IF NOT EXISTS movie_country_combined(")
                .append("country_id INT NOT NULL, ")
                .append("movie_id INT NOT NULL, ")
                .append("FOREIGN KEY(country_id) REFERENCES movie_country(id) ON DELETE CASCADE, ")
                .append("FOREIGN KEY(movie_id) REFERENCES movie(id) ON DELETE CASCADE, ")
                .append("CONSTRAINT uc_movie_country_combined UNIQUE (country_id, movie_id))")
                .toString();

        queries.addAll(Arrays.asList(
                createGenre,
                createPerson,
                createCountry,
                createLanguage,
                createMovie,
                createMoviePerson,
                createMovieGenre,
                createMovieLanguage,
                createMovieCountry,
                createWatched
        ));
        userConnection(dataBaseUrl, login, pass, queries);
    }
}
