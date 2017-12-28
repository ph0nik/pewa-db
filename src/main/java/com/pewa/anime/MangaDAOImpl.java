package com.pewa.anime;

import com.pewa.PewaType;
import com.pewa.common.Encounter;
import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MangaDAOImpl implements MangaDAO {
    private static final Logger log = LogManager.getLogger(MangaDAO.class);
    private static final String formatterString = "uuuu-MM-dd";
    private static final String addSuccess = "Manga item added  : ";
    private static final String updateSuccess = "Manga item updated : ";
    private static final String deleteSuccess = "Manga item deleted : ";
    private static final String nothingFound = "No manga with this Id found : ";
    private static final String alreadyInDb = "Manga item already in database : ";
    private final PewaType mangaType = PewaType.MANGA;
    private List<Encounter> output;
    private Integer rowsAffected;
    private Manga manga;

    @Override
    public Results addManga(Manga manga, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.insert(ConfigFactory.get("manga-mapper.insertManga"), manga);
            rowsAffected += session.insert(ConfigFactory.get("manga-mapper.insertPeopleMan"), manga);
            rowsAffected += session.insert(ConfigFactory.get("manga-mapper.insertPeopleBridgeMan"), manga);
            if (!manga.getGenres().isEmpty()) {
                rowsAffected += session.insert(ConfigFactory.get("manga-mapper.insertGenreMan"), manga);
                rowsAffected += session.insert(ConfigFactory.get("manga-mapper.insertGenreBridgeMan"), manga);
            }
            session.commit();
            results.setRowsAffected(rowsAffected);
            if (rowsAffected != 0) results.setReturnMessage(addSuccess + manga.getTitleRom());
            else results.setReturnMessage(alreadyInDb + manga.getTitleRom());
        }
        return results;
    }

    public Results updateManga(Manga manga, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.update(ConfigFactory.get("manga-mapper.updateAnime"), manga);
            session.commit();
        }
        results.setRowsAffected(rowsAffected);
        if (rowsAffected != 0) results.setReturnMessage(updateSuccess + manga.getTitleRom());
        else results.setReturnMessage(nothingFound + manga.getTitleRom());
        return results;
    }

    public Results deleteManga(Integer manga, Results results){
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.delete(ConfigFactory.get("manga-mapper.deleteAnime"), manga);
            session.commit();
            results.setRowsAffected(rowsAffected);
        }
        if (rowsAffected != 0) results.setReturnMessage(deleteSuccess + manga);
        else results.setReturnMessage(nothingFound + manga);
        return results;
    }

    @Override
    public Results getMangaByTitle(String query, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            query = new StringBuilder("%")
                    .append(query)
                    .append("%")
                    .toString();
            output = session.selectList(ConfigFactory.get("manga-mapper.byTitleMan"), query);
            session.commit();
        }
        output.forEach(x -> x.setType(mangaType));
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results getMangaById(Integer id, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            manga = session.selectOne(ConfigFactory.get("manga-mapper.ByIdMan"), id);
            session.commit();
            results.setEncounters(manga);
        }
        return results;
    }

    @Override
    public Results getMangaByPerson(Integer person, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("manga-mapper.byPersonMan"), person);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results getMangaByGenre(Integer genre, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("manga-mapper.byGenreMan"), genre);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results getMangaByYear(Request date, Results results) {
        Integer year = date.getYear();
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("manga-mapper.byYearMovie"), year);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }
}
