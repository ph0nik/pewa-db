package com.pewa;

import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitAllTables {

    private static final Logger log = LogManager.getLogger(InitAllTables.class);
    private StringBuilder logMessage;

    public void initTables() {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.BATCH,false)) {
            session.update("createPersonTable");
            session.update("createGenreTable");
            session.update("createLanguageTable");
            session.update("createCountryTable");

            session.update("createBookTable");
            session.update("createBookPerson");
            session.update("createBookGenre");
            session.update("createFormTable");

            session.update("createAnimeTable");
            session.update("createAnimePerson");
            session.update("createAnimeGenre");

            session.update("createMangaTable");
            session.update("createMangaPerson");
            session.update("createMangaGenre");

            session.update("createTvTable");
            session.update("createTvGenre");
            session.update("createTvEpisode");
            session.update("createTvPerson");

            session.update("createMovieTable");
            session.update("createMovieGenre");
            session.update("createMoviePerson");
            session.update("createMovieLanguage");
            session.update("createMovieCountry");
            session.update("createMovieEncounter");

            session.commit();
        }
    }

    public void dropTables() {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            session.update("dropAllTables");
            session.commit();
        }
    }

}