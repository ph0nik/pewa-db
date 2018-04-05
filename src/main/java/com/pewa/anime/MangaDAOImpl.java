package com.pewa.anime;

import com.pewa.InitAllTables;
import com.pewa.PewaType;
import com.pewa.common.AbstractMediaDAO;
import com.pewa.common.Request;
import com.pewa.common.Results;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MangaDAOImpl extends AbstractMediaDAO implements MangaDAO {
    private static final Logger log = LogManager.getLogger(MangaDAO.class);
    private List<String> mapperList = new ArrayList<>();
    private String infoField = "";

    public MangaDAOImpl() {
        super(PewaType.MANGA);
        tableManagement = new InitAllTables(PewaType.BOOK);
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
        return add(manga);
    }

    public Results updateManga(Manga manga) {
        infoField = manga.getTitleEng();
        mapperList = Arrays.asList("manga-mapper.updateAnime");
        return update(manga);
    }

    public Results deleteManga(Integer manga){
        mapperList = Arrays.asList("manga-mapper.deleteAnime");
        Results delete = delete(manga);
        return getTablesManagement().cleanAll(delete);
    }

    @Override
    public Results getMangaByTitle(String query) {
        mapperList = Arrays.asList("manga-mapper.byTitleMan");
        return search(query);
    }

    @Override
    public Results getMangaById(Integer id) {
        mapperList = Arrays.asList("manga-mapper.ByIdMan");
        return get(id);
    }

    @Override
    public Results getMangaByPerson(Integer person) {
        mapperList = Arrays.asList("manga-mapper.byPersonMan");
        return get(person);
    }

    @Override
    public Results getMangaByGenre(Integer genre) {
        mapperList = Arrays.asList("manga-mapper.byGenreMan");
        return get(genre);
    }

    @Override
    public Results getMangaByYear(Request date) {
        Integer year = date.getYear();
        mapperList = Arrays.asList("manga-mapper.byYearMovie");
        return get(year);
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
