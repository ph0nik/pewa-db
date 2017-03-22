package com.pewa;

import org.apache.ibatis.session.SqlSession;

public class InitAllTables {

    public void initTables() {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            session.update("createBookTable");
            session.update("createPersonTable");
            session.update("createBookPerson");
            session.update("createGenreTable");
            session.update("createBookGenre");
            session.update("createFormTable");
            session.update("createAnimeTable");
            session.update("createAnimePerson");
            session.update("createAnimeGenre");
            session.commit();
        } finally {
            session.close();
        }
    }
}
