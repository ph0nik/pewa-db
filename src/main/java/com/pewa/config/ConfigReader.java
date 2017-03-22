package com.pewa.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;

import java.io.File;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
            dbPass,
            userAgent,
            userAgentMusicBrainz,
            musicSearch,
            searchBookAlt,
            nameSpaceMusicBrainz,
            musicBrainzItem,
            aniListApiEndpoint,
            aniListClientId,
            aniListClientSecret,
            aniListPostTokenReq,
            aniListSearchAnime,
            aniListSearchManga,
            aniListAnimeItem,
            aniListMangaItem,
            aniListCharacters,
            coverByMusicBrainzId;


    private ConfigReader() {
    }

    public static void load() {
        File configFile = new File("src/main/resources/pewa-config.ini");
        try {
            HierarchicalINIConfiguration config = new HierarchicalINIConfiguration(configFile);
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


        } catch (ConfigurationException e) {
            System.out.println("Nie można znaleźć pliku: " + e);
        } catch (NoSuchElementException e) {
            System.out.println("[ERROR] " + e);
        }

    }


}
