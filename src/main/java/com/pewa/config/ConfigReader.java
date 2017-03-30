package com.pewa.config;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ConfigReader {
    public static String searchUrl;
    public static String searchMovie;
    public static String searchTv;
    public static String omdbUrl;
    public static String tvMaze;
    public static String tvMazeByImdbId;
    public static String tvMazeSummary;
    public static String tvMazeEpisodeList;
    public static String titleOrgId;
    public static String titlePlId;
    public static String searchBook;
    public static String bookItemUrl;
    public static String rootPass;
    public static String userName;
    public static String userPass;
    public static String dbUrlUser;
    public static String dbUrlRoot;
    public static String dbName;
    public static String dbLogin;
    public static String dbPass;
    public static String userAgent;
    public static String userAgentMusicBrainz;
    public static String musicSearch;
    public static String searchBookAlt;
    public static String nameSpaceMusicBrainz;
    public static String musicBrainzItem;
    public static String aniListApiEndpoint;
    public static String aniListClientId;
    public static String aniListClientSecret;
    public static String aniListPostTokenReq;
    public static String aniListSearchAnime;
    public static String aniListSearchManga;
    public static String aniListAnimeItem;
    public static String aniListMangaItem;
    public static String aniListCharacters;
    public static String coverByMusicBrainzId;
    public static String imgPath;

    //public static final String BLABLA = ConfigFactory.getConfigFactory().getString("asd");

    private static final String CONFIG_FILE = "src/main/resources/pewa-config.ini";
    private static final Logger log = LogManager.getLogger(ConfigReader.class);

    public static void load() {
        File configFile = new File(CONFIG_FILE);
        try {
            AbstractConfiguration config = new HierarchicalINIConfiguration(configFile);
            config.setThrowExceptionOnMissing(true);
            omdbUrl = config.getString("item.omdbLink");
            searchUrl = config.getString("search.imdbgetid");
            searchMovie = config.getString("search.imdbmovies");
            searchTv = config.getString("search.imdbtvshows");
            searchBook = config.getString("search.bookSearchUrl");
            searchBookAlt = config.getString("search.bookSearchUrlAlt");
            tvMaze = config.getString("item.tvmaze");
            tvMazeByImdbId = config.getString("item.tvmazeLookupImdb");
            tvMazeSummary = config.getString("item.tvmazeShows");
            tvMazeEpisodeList = config.getString("item.tvmazeEpisodes");
            titlePlId = config.getString("search.bookTitlePlId");
            titleOrgId = config.getString("search.bookTitleOrgId");
            bookItemUrl = config.getString("item.bookItemUrl");
            rootPass = config.getString("sql.dbPass");
            userName = config.getString("sql.userName");
            userPass = config.getString("sql.userPass");
            dbUrlUser = config.getString("sql.dbUrlUser");
            dbUrlRoot = config.getString("sql.dbUrlRoot");
            dbName = config.getString("sql.dbName");
            dbLogin = config.getString("sql.dbLogin");
            dbPass = config.getString("sql.dbPass");
            userAgent = config.getString("search.userAgent");
            userAgentMusicBrainz = config.getString("search.userAgentMB");
            musicSearch = config.getString("search.musicSearch");
            nameSpaceMusicBrainz = config.getString("search.nameSpaceMusicBrainz");
            musicBrainzItem = config.getString("item.musicBrainzItem");
            aniListApiEndpoint = config.getString("search.aniListApiEndpoint");
            aniListClientId = config.getString("search.aniListClientId");
            aniListClientSecret = config.getString("search.aniListClientSecret");
            aniListPostTokenReq = config.getString("search.aniListPostTokenReq");
            aniListSearchAnime = config.getString("search.aniListSearchAnime");
            aniListSearchManga = config.getString("search.aniListSearchManga");
            aniListAnimeItem = config.getString("item.aniListAnimeItem");
            aniListMangaItem = config.getString("item.aniListMangaItem");
            aniListCharacters = config.getString("item.aniListCharacters");
            coverByMusicBrainzId = config.getString("search.coverByMusicBrainzId");
            imgPath = config.getString("dbCache.imgPath");
        } catch (ConfigurationException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage(), e);
        }
    }
}
