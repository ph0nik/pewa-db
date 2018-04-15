package com.pewa.anime;

import com.pewa.InitAllTables;
import com.pewa.PewaType;
import com.pewa.common.*;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AnimeDAOImpl extends AbstractMediaDAO implements AnimeDAO {
    private static final Logger log = LogManager.getLogger(AnimeDAO.class);
    private String infoField = "";

    public AnimeDAOImpl() {
        super(PewaType.ANIME);
        tableManagement = new InitAllTables(PewaType.BOOK);
    }

    public String getInfoField() {
        return infoField;
    }

    public Results addAnime(Anime anime) {
        infoField = anime.getTitleEng();
        mapperList = Arrays.asList(
                "anime-mapper.insertAnime",
                "anime-mapper.insertPeopleAni",
                "anime-mapper.insertPeopleBridgeAni",
                "anime-mapper.insertGenreAni",
                "anime-mapper.insertGenreBridgeAni"
        );
        return add(anime);
    }

    @Override
    public Results updateAnime(Anime anime) {
        infoField = anime.getTitleEng();
        mapperList = Arrays.asList("anime-mapper.updateAnime");
        return update(anime);
    }

    // delete anime object from database
    @Override
    public Results deleteAnime(Integer anime) {
        mapperList = Arrays.asList("anime-mapper.deleteAnime");
        Results delete = delete(anime);
        return getTablesManagement().cleanAll(delete);
    }

    // returns list of anime objects based on query
    @Override
    public Results getAnimeByTitle(String query) {
        mapperList = Arrays.asList("anime-mapper.byTitleAni");
        return search(query);
    }

    @Override
    public Results getAnimeById(Integer databaseId) {
        mapperList = Arrays.asList("anime-mapper.ByIdAni");
        return get(databaseId);
    }

    @Override
    public Results getAnimeByPersonId(Integer personId) {
        mapperList = Arrays.asList("anime-mapper.byPersonAni");
        return get(personId);
    }

    @Override
    public Results getAnimeByGenreId(Integer genreId) {
        mapperList = Arrays.asList("anime-mapper.byGenreAni");
        return get(genreId);
    }

    @Override
    public Results getAnimeByYear(Integer year) {
        mapperList = Arrays.asList("anime-mapper.byYearAni");
        return get(year);

    }

}
