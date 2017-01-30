import com.sun.xml.internal.bind.v2.TODO;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by phonik on 2017-01-30.
 */
public class DatabaseMovie {


    static void userConnection(Movie movieInfo) {
        String dbName = ConfigReader.dbName;
        String login = ConfigReader.userName;
        String pass = ConfigReader.userPass;
        String dataBaseUrl = ConfigReader.dbUrlUser.concat(dbName);

        try (Connection conn = DriverManager.getConnection(dataBaseUrl, login, pass)) {
            try (PreparedStatement ps = addMovie(conn, movieInfo)) {
                 ps.executeUpdate();
            }
            try (PreparedStatement ps = addGenre(conn, movieInfo)) {
                 ps.executeBatch();
            }
            try (PreparedStatement ps = addMovieGenre(conn, movieInfo)) {
                ps.executeBatch();
            }
            try (PreparedStatement ps = addMovieGenre(conn, movieInfo)) {
                ps.executeBatch();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        List<String> people = Arrays.asList(movieInfo.getActors());
        people.addAll(Arrays.asList(movieInfo.getWriter()));
        people.addAll(Arrays.asList(movieInfo.getWriter()));

        String query = "INSERT INTO movie_person (name) VALUES(?)";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String person : people) {
            ps.setString(1, person);
        }
        return ps;
    }

    //// obiekt to poprawki
    private static PreparedStatement addMoviePerson(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_person_combine (person_id, movie_id, job) SELECT id, id FROM  movie_genre, movie where name=? and imdb_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String genre : movieInfo.getGenre()) {
            ps.setString(1, genre);
            ps.setString(2, movieInfo.getImdbID());
        }
        return ps;
    }
    private String[] director;
    private String[] writer;
    private String[] actors;
    private String[] language;
    private String[] country;
    private static PreparedStatement addGenre(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_genre (name) VALUES(?)";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String genre : movieInfo.getGenre()) {
            ps.setString(1, genre);
        }
        return ps;
    }
    private static PreparedStatement addMovieGenre(Connection conn, Movie movieInfo) throws SQLException {
        String query = "INSERT INTO movie_genre_combine (genre_id, movie_id) SELECT id, id FROM  movie_genre, movie where name=? and imdb_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        for(String genre : movieInfo.getGenre()) {
            ps.setString(1, genre);
            ps.setString(2, movieInfo.getImdbID());
        }
        return ps;
    }

}
