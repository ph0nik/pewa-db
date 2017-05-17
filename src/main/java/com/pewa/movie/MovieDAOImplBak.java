package com.pewa.movie;

import com.pewa.config.ConfigReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MovieDAOImpl implements MovieDAO {

    private String dbName = ConfigReader.dbName;
    private String login = ConfigReader.userName;
    private String pass = ConfigReader.userPass;
    private String dataBaseUrl = ConfigReader.dbUrlUser.concat(dbName);
    private String dataBaseUrlSslOff = dataBaseUrl.concat("?useSSL=false");

    /*
    * Method adds selected movie object to database
    * */
    public Boolean addMovie(Movie movieInfo) {
        Boolean added = false;
        try (Connection conn = DriverManager.getConnection(dataBaseUrlSslOff, login, pass)) {
            conn.setAutoCommit(false);
            try (PreparedStatement psChk = checkIfPresent(conn, movieInfo);
                 ResultSet rs = psChk.executeQuery()) {
                if (!rs.next()) {
                    try {
                        PreparedStatement ps = addMovie(conn, movieInfo);
                        ps.executeUpdate();
                        ps = addGenre(conn, movieInfo);
                        ps.executeBatch();
                        ps = addGenre(conn, movieInfo);
                        ps.executeBatch();
                        ps = addMovieGenre(conn, movieInfo);
                        ps.executeBatch();
                        ps = addPerson(conn, movieInfo);
                        ps.executeBatch();
                        ps = addMovieDirector(conn, movieInfo);
                        ps.executeBatch();
                        ps = addMovieWriter(conn, movieInfo);
                        ps.executeBatch();
                        ps = addMovieActors(conn, movieInfo);
                        ps.executeBatch();
                        ps = addLanguage(conn, movieInfo);
                        ps.executeBatch();
                        ps = addMovieLanguage(conn, movieInfo);
                        ps.executeBatch();
                        ps = addCountry(conn, movieInfo);
                        ps.executeBatch();
                        ps = addMovieCountry(conn, movieInfo);
                        ps.executeBatch();
                        ps.close();
                        conn.commit();
                        added = true;
                    } catch (SQLException e1) {
                        conn.rollback();
                        added = false;
                        e1.printStackTrace();
                    } finally {
                        conn.setAutoCommit(true);
                    }
                } else {
                    System.out.println("Aleready exists");
                    added = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return added;
    }

    /*
    * Method returns collection of movie objects that meet search requirements.
    * Items are being searched by title field.
    * */
    public Set<Movie> moviesByTitle(String query) {
        String statement = new StringBuilder("select movie.imdb_id from movie where movie.title like ")
                .append("'%")
                .append(query)
                .append("%'")
                .toString();
        return getMovieSet(searchBy(statement));
    }

    /*
    * Method returns collection of movie objects that meet search requirements.
    * Items are being searched by person field.
    */
    public Set<Movie> moviesByPerson(String query) {
        String statement = new StringBuilder("select movie.imdb_id from movie_person_combine join movie_person on movie_person.id = person_id join movie on movie.id = movie_id where movie_person.name like ")
                .append("'%")
                .append(query)
                .append("%'")
                .append(";")
                .toString();
        return getMovieSet(searchBy(statement));
    }

    /*
    * Method returns collection of movie objects that meet search requirements.
    * Items are being searched by genre field.
    */
    public Set<Movie> moviesByGenre(String query) {
        String statement = new StringBuilder("select movie.imdb_id from movie_genre_combine join movie_genre on movie_genre.id = genre_id join movie on movie.id = movie_id where movie_genre.genre like ")
                .append("'%")
                .append(query)
                .append("%'")
                .append(";")
                .toString();
        return getMovieSet(searchBy(statement));
    }

    /*
    * Method returns collection of movie objects that meet search requirements.
    * Items are being searched by language field.
    */
    public Set<Movie> moviesByLanguage(String query) {
        String statement = new StringBuilder("select movie.imdb_id from movie_language_combined join movie_language on movie_language.id = language_id join movie on movie.id = movie_id where movie_language.language like ")
                .append("'%")
                .append(query)
                .append("%'")
                .append(";")
                .toString();
        return getMovieSet(searchBy(statement));
    }

    /*
    * Method returns collection of movie objects that meet search requirements.
    * Items are being searched by year field.
    */
    public Set<Movie> moviesByYear(int x, int y) {
        String statement = new StringBuilder("select movie.imdb_id from movie where movie.year >= ")
                .append(x)
                .append(" and movie.year <= ")
                .append(y)
                .append(";")
                .toString();
        //return searchBy(statement);
        return getMovieSet(searchBy(statement));
    }

    /*
    * Helper method that returns collection of imdb identifiers, based on statement string.
    */
    private Set<String> searchBy(String statement) {
        Set<String> foundImdbIds = new TreeSet<>();
        try (Connection conn = DriverManager.getConnection(dataBaseUrlSslOff, login, pass)) {
            PreparedStatement psGenre = conn.prepareStatement(statement);
            ResultSet rsGenre = psGenre.executeQuery();
            while (rsGenre.next()) {
                foundImdbIds.add(rsGenre.getString("imdb_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundImdbIds;
    }

    /*
    * Helper method that creates movie object collection and iterates through given
    * list of imdb identifiers.
    * */
    private Set<Movie> getMovieSet(Set<String> imdbIdSet) {
        Set<Movie> movieSet = new TreeSet<>();
        for (String imdbId : imdbIdSet) {
            movieSet.add(getMovie(imdbId));
        }
        return movieSet;
    }
    /*
    * Helper method that returns movie object based on imdb identifier.
    * */

    private Movie getMovie(String imdbId) {
        Movie movie = new Movie();
        try (Connection conn = DriverManager.getConnection(dataBaseUrlSslOff, login, pass)) {
            String statement = "select * from movie where imdb_id = ?;";
            PreparedStatement psSum = conn.prepareStatement(statement);
            psSum.setString(1, imdbId);
            ResultSet rsSum = psSum.executeQuery();

            while (rsSum.next()) {
                movie.setTitle(rsSum.getString("title"));
                movie.setYear(rsSum.getInt("year"));
                movie.setAgeRating(rsSum.getString("rating"));
                movie.setRelDate(rsSum.getString("release_date"));
                movie.setRuntime(rsSum.getString("runtime"));
                movie.setPlot(rsSum.getString("plot"));
                movie.setAwards(rsSum.getString("awards"));
                movie.setPoster(rsSum.getString("poster"));
                movie.setMetascore(rsSum.getString("metascore"));
                movie.setImdbRating(rsSum.getString("imdb_rating"));
                movie.setImdbVotes(rsSum.getString("imdb_votes"));
                movie.setImdbID(rsSum.getString("imdb_id"));

                statement = "select movie_person.name, movie_person_combine.job from movie_person_combine join movie_person on movie_person.id = person_id join movie on movie.id = movie_id where movie.imdb_id = ?;";
                PreparedStatement psForeignTables = conn.prepareStatement(statement);
                psForeignTables.setString(1, movie.getImdbID());
                ResultSet rsForeignTables = psForeignTables.executeQuery();
                Set<String> actors = new TreeSet<>();
                Set<String> directors = new TreeSet<>();
                Set<String> writers = new TreeSet<>();
                while (rsForeignTables.next()) {
                    if (rsForeignTables.getString("job").equals("actor")) {
                        actors.add(rsForeignTables.getString("name"));
                    } else if (rsForeignTables.getString("job").equals("director")) {
                        directors.add(rsForeignTables.getString("name"));
                    } else {
                        writers.add(rsForeignTables.getString("name"));
                    }
                }
                movie.setActors(actors);
                movie.setDirector(directors);
                movie.setWriter(writers);

                statement = "select movie_country.country from movie_country_combined join movie_country on movie_country.id = country_id join movie on movie.id = movie_id where movie.imdb_id = ?;";
                psForeignTables = conn.prepareStatement(statement);
                psForeignTables.setString(1, movie.getImdbID());
                rsForeignTables = psForeignTables.executeQuery();
                Set<String> country = new TreeSet<>();
                while (rsForeignTables.next()) {
                    country.add(rsForeignTables.getString("country"));
                }
                movie.setCountry(country);

                statement = "select movie_genre.genre from movie_genre_combine join movie_genre on movie_genre.id = genre_id join movie on movie.id = movie_id where movie.imdb_id = ?;";
                psForeignTables = conn.prepareStatement(statement);
                psForeignTables.setString(1, movie.getImdbID());
                rsForeignTables = psForeignTables.executeQuery();
                Set<String> genre = new TreeSet<>();
                while (rsForeignTables.next()) {
                    genre.add(rsForeignTables.getString("genre"));
                }
                movie.setGenre(genre);

                statement = "select movie_language.language from movie_language_combined join movie_language on movie_language.id = language_id join movie on movie.id = movie_id where movie.imdb_id = ?;";
                psForeignTables = conn.prepareStatement(statement);
                psForeignTables.setString(1, movie.getImdbID());
                rsForeignTables = psForeignTables.executeQuery();
                Set<String> language = new TreeSet<>();
                while (rsForeignTables.next()) {
                    language.add(rsForeignTables.getString("language"));
                }
                movie.setLanguage(language);
                psForeignTables.close();
            }
            psSum.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

/*
* Helper method, creates statement for querying database for specified movie object.
* Returns PreparedStatement object for further processing.
* */
    private PreparedStatement checkIfPresent(Connection conn, Movie movieInfo) throws SQLException {
        String query = "SELECT * FROM movie WHERE imdb_id=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, movieInfo.getImdbID());
        return ps;
    }
/*
* Helper method, creates statement for adding all the properties of movie object that have singular value to movie table.
* Returns PreparedStatement object for further processing.
* */
    private PreparedStatement addMovie(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie (title, year, rating, release_date, runtime, plot, awards, poster, metascore, imdb_rating, imdb_votes, imdb_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
/*
* Helper method, creates statement for adding person property to person table.
* Returns PreparedStatement object for further pricessing.
* */
    private PreparedStatement addPerson(Connection conn, Movie movieInfo) throws SQLException {
        List<String> tempListOfPeople = new ArrayList(movieInfo.getDirector());
        tempListOfPeople.addAll(movieInfo.getWriter());
        tempListOfPeople.addAll(movieInfo.getActors());
        tempListOfPeople = tempListOfPeople.stream().distinct().collect(Collectors.toList());
        String query = "INSERT INTO movie_person (name) SELECT ? WHERE NOT EXISTS(SELECT * FROM movie_person WHERE name=?);";
        PreparedStatement ps = conn.prepareStatement(query);
        for (String person : tempListOfPeople) {
            ps.setString(1, person);
            ps.setString(2, person);
            ps.addBatch();
        }
        return ps;
    }
/*
* Helper method, creates statement for connection between movie and person tables based on foreign keys. (directors)
* Returns PreparedStatement object for further processing.
* */
    private PreparedStatement addMovieDirector(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_person_combine (person_id, movie_id, job) SELECT movie_person.id, movie.id, 'director' FROM  movie_person, movie WHERE name=? AND imdb_id=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        for (String person : movieInfo.getDirector()) {
            ps.setString(1, person);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }
/*
* Helper method, creates statement for connection between movie and person tables based on foreign keys. (writers)
* Returns PreparedStatement object for further processing.
* */
    private PreparedStatement addMovieWriter(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_person_combine (person_id, movie_id, job) SELECT movie_person.id, movie.id, 'writer' FROM  movie_person, movie WHERE name=? AND imdb_id=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        for (String person : movieInfo.getWriter()) {
            ps.setString(1, person);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }
/*
* Helper method, creates statement for connection between movie and person tables based on foreign keys. (actors)
* Returns PreparedStatement object for further processing.
* */
    private PreparedStatement addMovieActors(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_person_combine (person_id, movie_id, job) SELECT movie_person.id, movie.id, 'actor' FROM  movie_person, movie WHERE name=? AND imdb_id=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        for (String person : movieInfo.getActors()) {
            ps.setString(1, person);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }
/*
* Helper method, creates statement for adding language properties to language table.
* Returns PreparedStatement object for further processing.
* */
    private PreparedStatement addLanguage(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_language (language) SELECT ? WHERE NOT EXISTS(SELECT * FROM movie_language WHERE language=?);";
        PreparedStatement ps = conn.prepareStatement(query);
        for (String language : movieInfo.getLanguage()) {
            ps.setString(1, language);
            ps.setString(2, language);
            ps.addBatch();
        }
        return ps;
    }
/*
* Helper method, creates statement for connection between movie and language table based on foreign keys.
* Returns PreparedStatement object for further processing.
* */
    private PreparedStatement addMovieLanguage(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_language_combined (language_id, movie_id) SELECT movie_language.id, movie.id FROM  movie_language, movie WHERE language=? AND imdb_id=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        for (String language : movieInfo.getLanguage()) {
            ps.setString(1, language);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }
/*
* Helper method, creates statement for adding country properties to country table.
* Returns PreparedStatement object for further processing.
* */
    private PreparedStatement addCountry(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_country (country) SELECT ? WHERE NOT EXISTS(SELECT * FROM movie_country WHERE country=?);";
        PreparedStatement ps = conn.prepareStatement(query);
        for (String country : movieInfo.getCountry()) {
            ps.setString(1, country);
            ps.setString(2, country);
            ps.addBatch();
        }
        return ps;
    }
/*
* Helper method, creates statement for connection between movie and country table, based on foreign keys.
* Returns PreparedStatement object for further processing.
* */
    private PreparedStatement addMovieCountry(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_country_combined (country_id, movie_id) SELECT movie_country.id, movie.id FROM  movie_country, movie WHERE country=? AND imdb_id=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        for (String country : movieInfo.getCountry()) {
            ps.setString(1, country);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }
/*
* Helper method, creates statement for adding genre properties to genre table.
* Returns PreparedStatement object for further processing.
* */
    private PreparedStatement addGenre(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_genre (genre) SELECT ? WHERE NOT EXISTS(SELECT * FROM movie_genre WHERE genre=?);";
        PreparedStatement ps = conn.prepareStatement(query);
        for (String genre : movieInfo.getGenre()) {
            ps.setString(1, genre);
            ps.setString(2, genre);
            ps.addBatch();
        }
        return ps;
    }
/*
* Helper method, creates statement for connecting movie and genre tables, based on foreign keys.
* Returns PreparedStatement object for further processing.
* */
    private PreparedStatement addMovieGenre(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_genre_combine (genre_id, movie_id) SELECT movie_genre.id, movie.id FROM  movie_genre, movie  where movie_genre.genre=? and movie.imdb_id=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        for (String genre : movieInfo.getGenre()) {
            ps.setString(1, genre);
            ps.setString(2, movieInfo.getImdbID());
            ps.addBatch();
        }
        return ps;
    }

}
