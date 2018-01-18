package com.pewa;

import com.pewa.common.Results;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;


@Component
public class InitAllTables {

    private static final Logger log = LogManager.getLogger(InitAllTables.class);
    private static final String imgPath = ConfigFactory.get("dbCache.imgPath");
    private StringBuilder logMessage;
    private Integer rowsAffected;
    private Results results = new Results();
    private final String cleanupSuccess = "Cleanup complete [OK]";
    private final String cleanupFail = "Nothing found";
    private final String cleanPerson = "Deleting Person objects with no relation...";
    private final String cleanLanguage = "Deleting Language objects with no relation...";
    private final String cleanCountry = "Deleting Country objects with no relation...";
    private final String cleanGenre = "Deleting Genre objects with no relation...";
    private final String cleanStatus = "Deleting Status objects with no relation...";


    public void initTables() {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.BATCH, false)) {
            SubnodeConfiguration iniSection = ConfigFactory.getIniSection("init-sql-tables");
            iniSection.getKeys()
                    .forEachRemaining(
                            key -> session.update(ConfigFactory.get("init-sql-tables." + key))
                    );
            session.commit();
        }
    }

    public void dropTables() {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            session.update("dropAllTables");
            session.commit();
        }
    }

    public Results cleanAll(Results results) {
        rowsAffected = results.getRowsAffected();
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            rowsAffected += session.delete(ConfigFactory.get("init-sql-tables.personCleanUp"));
            log.debug(cleanPerson);
            rowsAffected += session.delete(ConfigFactory.get("init-sql-tables.genreCleanUp"));
            log.debug(cleanGenre);
            rowsAffected += session.delete(ConfigFactory.get("init-sql-tables.countryCleanUp"));
            log.debug(cleanCountry);
            rowsAffected += session.delete(ConfigFactory.get("init-sql-tables.languageCleanUp"));
            log.debug(cleanLanguage);
            rowsAffected += session.delete(ConfigFactory.get("init-sql-tables.statusCleanUp"));
            log.debug(cleanStatus);
            session.commit();
        }
        results.setRowsAffected(rowsAffected);
        if (rowsAffected != 0) results.setReturnMessage(cleanupSuccess);
        else results.setReturnMessage(cleanupFail);
        return results;
    }

    public void checkForUnconnectedFiles() {
        File folder = new File(imgPath);
        List<File> listOfFiles = Arrays.asList(folder.listFiles());

        listOfFiles.forEach(System.out::println);
    }



}
