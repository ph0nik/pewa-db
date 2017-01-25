import java.security.Key;
import java.sql.*;

/**
 * Created by phonik on 2017-01-17.
 *
 *- zapytaj o haslo
 * - polacz z baza danych
 * - sprawdz czy istnieje baza o okreslonej nazwie, jesli nie utworz nowa jesli tak wejdz
 *
 */
class DatabaseInit {

    private static Connection rootConnection() {
        String urlRoot = ConfigReader.dbUrlRoot;
        String passRoot = ConfigReader.rootPass;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(urlRoot, "root", passRoot);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return conn;
        }

    }
    static void createDb() throws Exception {
        Connection conn = rootConnection();
        String createDb = new StringBuilder("CREATE DATABASE IF NOT EXISTS ")
                .append(ConfigReader.dbName)
                .toString();
        Statement st = conn.createStatement();
        st.executeUpdate(createDb);
        st.close();
        conn.close();
        if (conn != null) conn.close();
    }

    static void createUser() throws Exception {
        Connection conn = rootConnection();
        String createUser = new StringBuilder("CREATE USER IF NOT EXISTS '")
                .append(ConfigReader.userName)
                .append("'@'localhost' identified by '")
                .append(ConfigReader.userPass)
                .append("'")
                .toString();
        Statement st = conn.createStatement();
        st.executeUpdate(createUser);
        st.close();
        if (conn != null) conn.close();
    }

    static void grantPrivs() throws Exception {
        Connection conn = rootConnection();
        String grantPriv = new StringBuffer("GRANT ALL ON ")
                .append(ConfigReader.dbName)
                .append(".* TO '")
                .append(ConfigReader.userName)
                .append("'@'localhost'")
                .toString();
        Statement st = conn.createStatement();
        st.executeUpdate(grantPriv);
        st.close();
        if (conn != null) conn.close();
    }

    private static Connection userConnection() {
        String dbName = ConfigReader.dbName;
        String login = ConfigReader.userName;
        String pass = ConfigReader.userPass;
        String dataBaseUrl = ConfigReader.dbUrlUser.concat(dbName);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dataBaseUrl, login, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return conn;
        }
    }
    static void createGenreTable() throws Exception {
        Connection conn = userConnection();
        String createGenre = "CREATE TABLE IF NOT EXISTS genre(genreId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, genreName VARCHAR(20))";
        Statement st = conn.createStatement();
        st.executeUpdate(createGenre);
        st.close();
        if (conn != null) conn.close();

    }

    static void createPersonTable() throws SQLException {
        Connection conn = userConnection();
        String createPerson = "CREATE TABLE IF NOT EXISTS person(personId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, firstName VARCHAR(20), lastName VARCHAR(30))";
        Statement st = conn.createStatement();
        st.executeUpdate(createPerson);
        st.close();
        if (conn != null) conn.close();
    }

    static void createCountryTable() throws SQLException {
        Connection conn = userConnection();
        String createCountry = "CREATE TABLE IF NOT EXISTS country(countryId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(30))";
        Statement st = conn.createStatement();
        st.executeUpdate(createCountry);
        st.close();
        if (conn != null) conn.close();
    }
    /*static void createMovieTable() throws Exception {

            Connection conn = userConnection();

            String createTables = new StringBuilder()
                    "CREATE TABLE movies (
                            1, "movieDbId INT NOT NULL AUTO_INCREMENT"
                            2, "title VARCHAR(30)"
                            3, "year INT NOT NULL"
                            4, "ageRating VARCHAR(10)"
                            5, "relDate VARCHAR(20)"
            6, "runtime VARCHAR(10)"
            private String[] genre;
            private String[] director;
            private String[] writer;
            private String[] actors;
            private String plot;
            private String[] language;
            private String[] country;
            private String awards;
            private String poster;
            private String metascore;
            private String imdbRating;
            private String imdbVotes;
            private String imdbID;
                            ")"

            if (conn != null) conn.close();

    }*/
}
