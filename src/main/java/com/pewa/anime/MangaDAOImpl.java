package com.pewa.anime;

import com.pewa.PewaType;
import com.pewa.common.Encounter;
import com.pewa.common.MediaDAO;
import com.pewa.common.Request;
import com.pewa.common.Results;
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
public class MangaDAOImpl extends MediaDAO implements MangaDAO {
    private static final Logger log = LogManager.getLogger(MangaDAO.class);
    private List<String> mapperList = new ArrayList<>();
    private String infoField = "";

    public MangaDAOImpl() {
        super(PewaType.MANGA);
    }

    @Override
    public Results addManga(Manga manga) {
        infoField = manga.getTitleEng();
        mapperList = Arrays.asList(
                "manga-mapper.insertManga",
                "manga-mapper.insertPeopleMan",
                "manga-mapper.insertPeopleBridgeMan",
                "manga-mapper.insertGenreMan",
                "manga-mapper.insertGenreBridgeMan"
        );
        return super.add(manga);
    }

    public Results updateManga(Manga manga) {
        infoField = manga.getTitleEng();
        mapperList = Arrays.asList("manga-mapper.updateAnime");
        return super.update(manga);
    }

    public Results deleteManga(Integer manga){
        mapperList = Arrays.asList("manga-mapper.deleteAnime");
        return super.delete(manga);
    }

    @Override
    public Results getMangaByTitle(String query) {
        mapperList = Arrays.asList("manga-mapper.byTitleMan");
        return super.search(query);
    }

    @Override
    public Results getMangaById(Integer id) {
        mapperList = Arrays.asList("manga-mapper.ByIdMan");
        return super.get(id);
    }

    @Override
    public Results getMangaByPerson(Integer person) {
        mapperList = Arrays.asList("manga-mapper.byPersonMan");
        return super.get(person);
    }

    @Override
    public Results getMangaByGenre(Integer genre) {
        mapperList = Arrays.asList("manga-mapper.byGenreMan");
        return super.get(genre);
    }

    @Override
    public Results getMangaByYear(Request date) {
        Integer year = date.getYear();
        mapperList = Arrays.asList("manga-mapper.byYearMovie");
        return super.get(year);
    }

    @Override
    public List<String> getMapperList() {
        return mapperList;
    }

    @Override
    public String getInfoField() {
        return infoField;
    }
}
