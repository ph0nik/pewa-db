import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by phonik on 2017-01-17.
 *
 *- zapytaj o haslo
 * - polacz z baza danych
 * - sprawdz czy istnieje baza o okreslonej nazwie, jesli nie utworz nowa jesli tak wejdz
 *
 */
class DatabaseInit {

    static void createDb() throws Exception {
        Connection conn = null;

        try {
//            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(ConfigReader.dbUrlRoot, "root", ConfigReader.rootPass);
            String createDb = new StringBuilder("CREATE DATABASE IF NOT EXISTS ")
                                    .append(ConfigReader.dbName)
                                    .toString();
            Statement st = conn.createStatement();
            st.executeUpdate(createDb);
            st.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
    }

    static void createUser() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(ConfigReader.dbUrlRoot, "root", ConfigReader.rootPass);
            String createUser = new StringBuilder("CREATE USER IF NOT EXISTS '")
                    .append(ConfigReader.userName)
                    .append("'@'localhost' identified by '")
                    .append(ConfigReader.userPass)
                    .append("'")
                    .toString();
            Statement st = conn.createStatement();
            st.executeUpdate(createUser);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void grantPrivs() throws Exception {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(ConfigReader.dbUrlRoot, "root", ConfigReader.rootPass);
            String grantPriv = new StringBuffer("GRANT ALL ON ")
                    .append(ConfigReader.dbName)
                    .append(".* TO '")
                    .append(ConfigReader.userName)
                    .append("'@'localhost'")
                    .toString();
            Statement st = conn.createStatement();
            st.executeUpdate(grantPriv);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void connectRoot() {

    }

    static void createTable() throws Exception {
        String dbName = ConfigReader.dbName;
        String login = ConfigReader.userName;
        String pass = ConfigReader.userPass;
        String dataBaseUrl = ConfigReader.dbUrlUser.concat(dbName);
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(dataBaseUrl, login, pass);
//            String makeTable = new StringBuilder()


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
    }
}
