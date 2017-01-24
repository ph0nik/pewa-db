import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;

import java.io.File;

/**
 * Created by phonik on 2017-01-17.
 */
public class ConfigReader {
    public static String searchUrl,
            searchMovie,
            searchTv,
            omdbUrl,
            tvMaze,
            tvMazeByImdbId,
            tvMazeSummary,
            tvMazeEpisodeList,
            titleOrgId,
            titlePlId,
            searchBook,
            bookItemUrl,
            rootPass,
            userName,
            userPass,
            dbUrlUser,
            dbUrlRoot,
            dbName,
            dbLogin,
            dbPass;

    private ConfigReader() {
    }
    public static Boolean load() {
        File configFile = new File("pewa-config.ini");
        Boolean status = false;
        try {
            HierarchicalINIConfiguration config = new HierarchicalINIConfiguration(configFile);
            omdbUrl = config.getProperty("item.omdbLink").toString();
            searchUrl = config.getProperty("search.imdbgetid").toString();
            searchMovie = config.getProperty("search.imdbmovies").toString();
            searchTv = config.getProperty("search.imdbtvshows").toString();
            searchBook = config.getProperty("search.bookSearchUrl").toString();
            tvMaze = config.getProperty("item.tvmaze").toString();
            tvMazeByImdbId = config.getProperty("item.tvmazeLookupImdb").toString();
            tvMazeSummary = config.getProperty("item.tvmazeShows").toString();
            tvMazeEpisodeList = config.getProperty("item.tvmazeEpisodes").toString();
            titleOrgId = config.getProperty("search.bookTitlePlId").toString();
            titlePlId = config.getProperty("search.bookTitleOrgId").toString();
            bookItemUrl = config.getProperty("item.bookItemUrl").toString();
            rootPass = config.getProperty("sql.dbPass").toString();
            userName = config.getProperty("sql.userName").toString();
            userPass = config.getProperty("sql.userPass").toString();
            dbUrlUser = config.getProperty("sql.dbUrlUser").toString();
            dbUrlRoot = config.getProperty("sql.dbUrlRoot").toString();
            dbName = config.getProperty("sql.dbName").toString();
            dbLogin = config.getProperty("sql.dbLogin").toString();
            dbPass = config.getProperty("sql.dbPass").toString();
            status = true;

        } catch (ConfigurationException e) {
            status = false;
            System.out.println("Nie można znaleźć pliku: " + e);

        } finally {
            return status;
        }

    }



}
