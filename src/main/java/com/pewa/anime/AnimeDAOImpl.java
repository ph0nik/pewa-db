package com.pewa.anime;

import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AnimeDAOImpl extends MediaDAO implements AnimeDAO {
    private static final Logger log = LogManager.getLogger(AnimeDAO.class);
    private static final String formatterString = "uuuu-MM-dd";
    private static final String addSuccess = "Anime item added  : ";
    private static final String updateSuccess = "Anime item updated : ";
    private static final String deleteSuccess = "Anime item deleted : ";
    private static final String nothingFound = "No anime with this Id found : ";
    private static final String alreadyInDb = "Anime item already in database : ";
    private final PewaType animeType = PewaType.ANIME;
    private List<String> mapperList = new ArrayList<>();
    private String returnMessage = "";
    private List<Encounter> output;
    private String infoField = "";

    public AnimeDAOImpl() {
        super(PewaType.ANIME);
    }

    public String getInfoField() {
        return infoField;
    }

    public Results addAnime(Anime anime, Results results) {
        infoField = anime.getTitleEng();
        mapperList = Arrays.asList(
                "anime-mapper.insertAnime",
                "anime-mapper.insertPeopleAni",
                "anime-mapper.insertPeopleBridgeAni",
                "anime-mapper.insertGenreAni",
                "anime-mapper.insertGenreBridgeAni"
        );
        return super.add(anime, results);
//        rowsAffected = 0;
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            rowsAffected += session.insert(ConfigFactory.get("anime-mapper.insertAnime"), anime);
//            rowsAffected += session.insert(ConfigFactory.get("anime-mapper.insertPeopleAni"), anime);
//            rowsAffected += session.insert(ConfigFactory.get("anime-mapper.insertPeopleBridgeAni"), anime);
//            if (!anime.getGenres().isEmpty()) {
//                rowsAffected += session.insert(ConfigFactory.get("anime-mapper.insertGenreAni"), anime);
//                rowsAffected += session.insert(ConfigFactory.get("anime-mapper.insertGenreBridgeAni"), anime);
//            }
//            session.commit();
//            results.setRowsAffected(rowsAffected);
//            if (rowsAffected != 0) results.setReturnMessage(addSuccess + anime.getTitleRom());
//            else results.setReturnMessage(alreadyInDb + anime.getTitleRom());
//        }
//        return results;
    }

    @Override
    public Results updateAnime(Anime anime, Results results) {
        infoField = anime.getTitleEng();
        mapperList = Arrays.asList("anime-mapper.updateAnime");
        return super.update(anime, results);
//        rowsAffected = 0;
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            rowsAffected += session.update(ConfigFactory.get("anime-mapper.updateAnime"), anime);
//            session.commit();
//        }
//        results.setRowsAffected(rowsAffected);
//        if (rowsAffected != 0) results.setReturnMessage(updateSuccess + anime.getTitleRom());
//        else results.setReturnMessage(nothingFound + anime.getTitleRom());
//        return results;
    }

    @Override
    public Results deleteAnime(Integer anime, Results results) {
        mapperList = Arrays.asList("anime-mapper.deleteAnime");
        return super.delete(anime, results);
//        rowsAffected = 0;
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            rowsAffected += session.delete(ConfigFactory.get("anime-mapper.deleteAnime"), anime);
//            session.commit();
//            results.setRowsAffected(rowsAffected);
//        }
//        if (rowsAffected != 0) results.setReturnMessage(deleteSuccess + anime);
//        else results.setReturnMessage(nothingFound + anime);
//        return results;
    }

    @Override
    public Results getAnimeByTitle(String query, Results results) {
        mapperList = Arrays.asList("anime-mapper.byTitleAni");
        return super.search(query, results);
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            query = new StringBuilder("%")
//                    .append(query)
//                    .append("%")
//                    .toString();
//            output = session.selectList(ConfigFactory.get("anime-mapper.byTitleAni"), query);
//            session.commit();
//        }
//        output.forEach(x -> x.setType(animeType));
//        output.forEach(results::setEncounters);
//        return results;
    }



    @Override
    public Results getAnimeById(Integer databaseId, Results results) {
        mapperList = Arrays.asList("anime-mapper.ByIdAni");
        return super.get(databaseId, results);
//        Anime anime;
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            anime = session.selectOne(ConfigFactory.get("anime-mapper.ByIdAni"), databaseId);
//            session.commit();
//            results.setEncounters(anime);
//        }
//        return results;
    }

    @Override
    public Results getAnimeByPersonId(Integer personId, Results results) {
        mapperList = Arrays.asList("anime-mapper.byPersonAni");
        return super.get(personId, results);
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            output = session.selectList(ConfigFactory.get("anime-mapper.byPersonAni"), personId);
//            session.commit();
//        }
//        output.forEach(results::setEncounters);
//        return results;
    }

    @Override
    public Results getAnimeByGenreId(Integer genreId, Results results) {
        mapperList = Arrays.asList("anime-mapper.byGenreAni");
        return super.get(genreId, results);
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            output = session.selectList(ConfigFactory.get("anime-mapper.byGenreAni"), genreId);
//            session.commit();
//        }
//        output.forEach(results::setEncounters);
//        return results;
    }

    // TODO anime dates are specially formated so year alone cannot be compared with values in database
    @Override
    public Results getAnimeByYear(Request date, Results results) {
        Integer year = date.getYear();
        mapperList = Arrays.asList("anime-mapper.byYearAni");
        return super.get(year, results);
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            output = session.selectList(ConfigFactory.get("anime-mapper.byYearAni"), year);
//            session.commit();
//        }
//        output.forEach(results::setEncounters);
//        return results;
    }

    @Override
    public List<String> getMapperList() {
        return mapperList;
    }
}
