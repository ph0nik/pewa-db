package com.pewa.tv;

import com.pewa.InitAllTables;
import com.pewa.MediaModel;
import com.pewa.PewaType;
import com.pewa.common.AbstractMediaDAO;
import com.pewa.common.Results;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by phonik on 2017-03-31.
 */
@Component
public class TvShowDAOImpl extends AbstractMediaDAO implements TvShowDAO {
    private List<TvShowEpisode> output = new ArrayList<>();
    private List<String> mapperList = new ArrayList<>();
    private String infoField = "";

    private static final Logger log = LogManager.getLogger(TvShowDAOImpl.class);
    private static final String formatterString = "uuuu-MM-dd";
    private static final String addSuccess = "TvShow item added  : ";
    private static final String updateSuccess = "TvShow item updated : ";
    private static final String deleteSuccess = "TvShow item deleted : ";
    private static final String nothingFound = "No tvshow with this Id found : ";
    private static final String alreadyInDb = "TvShow item already in database : ";
    private final PewaType tvType = PewaType.TVSERIES;
    private Integer rowsAffected;
    private TvShowSummary tvShowSummary;

    public TvShowDAOImpl() {
        super(PewaType.TVSERIES);
        tableManagement = new InitAllTables(PewaType.BOOK);
    }

    @Override
    public Results addTvShow(TvShowSummary tvshow) {
        infoField = tvshow.getTitle();
        mapperList = Arrays.asList(
                "tv-mapper.insertTvSummary",
                "tv-mapper.insertGenreTv",
                "tv-mapper.insertGenreBridgeTv",
                "tv-mapper.insertTvEpisode",
                "tv-mapper.insertPersonTv",
                "tv-mapper.insertPersonTvBridge",
                "tv-mapper.insertLanguageTv",
                "tv-mapper.insertLanguageBridgeTv",
                "tv-mapper.insertCountryTv",
                "tv-mapper.insertCountryBridgeTv"
        );
        return super.add(tvshow);
//        rowsAffected = 0;
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertTvSummary"), tvshow);
//            if (!tvshow.getGenres().isEmpty()) {
//                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertGenreTv"), tvshow);
//                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertGenreBridgeTv"), tvshow);
//            }
//            if (!tvshow.getEpisodes().isEmpty()) {
//                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertTvEpisode"), tvshow);
//            }
//            if (!tvshow.getStaff().isEmpty()) {
//                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertPersonTv"), tvshow);
//                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertPersonTvBridge"), tvshow);
//            }
//            rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertLanguageTv"), tvshow);
//            rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertLanguageBridgeTv"), tvshow);
//            rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertCountryTv"), tvshow);
//            rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertCountryBridgeTv"), tvshow);
//            session.commit();
//            results.setRowsAffected(rowsAffected);
//            if (rowsAffected != 0)
//                results.setReturnMessage(addSuccess + tvshow.getTitle() + " " + tvshow.getPremiered().getYear());
//            else results.setReturnMessage(alreadyInDb + tvshow.getTitle() + " " + tvshow.getPremiered().getYear());
//        }
//        return results;
    }

    @Override
    public Results updateTvShow(TvShowSummary tvshow) {
        infoField = tvshow.getTitle();
        mapperList = Arrays.asList(
                "tv-mapper.updateTv",
                "tv-mapper.insertTvEpisode"
        );
        return super.update(tvshow);
//        rowsAffected = 0;
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            rowsAffected += session.update(ConfigFactory.get("tv-mapper.updateTv"), tvshow);
//            if (!tvshow.getEpisodes().isEmpty()) {
//                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertTvEpisode"), tvshow);
//            }
//            session.commit();
//        }
//        results.setRowsAffected(rowsAffected);
//        if (rowsAffected != 0) results.setReturnMessage(updateSuccess + tvshow.getTitle() + " " + tvshow.getPremiered().getYear());
//        else results.setReturnMessage(nothingFound + tvshow.getTitle() + " " + tvshow.getPremiered().getYear());
//        return results;
    }

    @Override
    public Results deleteTvShow(Integer tvshowid) {
        mapperList = Arrays.asList(
                "tv-mapper.deleteTv",
                "tv-mapper.deleteTvEpisodes"
                );
        return super.delete(tvshowid);
//        rowsAffected = 0;
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            rowsAffected += session.delete(ConfigFactory.get("tv-mapper.deleteTv"), tvshowid);
//            rowsAffected += session.delete(ConfigFactory.get("tv-mapper.deleteTvEpisodes"), tvshowid);
//            session.commit();
//            results.setRowsAffected(rowsAffected);
//        }
//        if (rowsAffected != 0) results.setReturnMessage(deleteSuccess + tvshowid);
//        else results.setReturnMessage(nothingFound + tvshowid);
//        return results;
    }

    @Override
    public Results tvshowByTitle(String query) {
        mapperList = Arrays.asList("tv-mapper.byTitleTv");
        return super.search(query);
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            query = new StringBuilder("%")
//                    .append(query)
//                    .append("%")
//                    .toString();
//            output = session.selectList(ConfigFactory.get("tv-mapper.byTitleTv"), query);
//            /*output.forEach(item -> session.selectList("getEpisodes", item.getTvMazeId()));
//            for(TvShowSummary item : output) {
//                item.setEpisodes(session.selectList("getEpisodes", item.getTvMazeId()));
//            }*/
//            session.commit();
//        }
//        output.forEach(x -> x.setType(tvType));
//        output.forEach(results::setEncounters);
//        return results;
    }


    @Override
    public TvShowSummary getTvShowByExternalId(Integer id) {
        return null;
    }

    @Override
    public Results tvshowById(Integer dbId) {
        mapperList = Arrays.asList("tv-mapper.getEpisodes");
        List<MediaModel> episodes = super.get(dbId).getEncounters();
        mapperList = Arrays.asList("tv-mapper.byIdTv");
        Results tvResults = super.get(dbId);
        for (MediaModel ep : episodes) {
            ((TvShowSummary) tvResults.getEncounters().get(0)).setEpisodes((TvShowEpisode) ep);
        }
        return tvResults;

//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            tvShowSummary = session.selectOne(ConfigFactory.get("tv-mapper.byIdTv"), dbId);
//            List<MediaModel> episodes = session.selectList("getEpisodes", dbId);
//            tvShowSummary.setEpisodes((TvShowEpisode) episodes);
//            session.commit();
//            results.setEncounters(tvShowSummary);
//        }
//        return results;
    }

    @Override
    public Results tvshowByPersonId(Integer person) {
        mapperList = Arrays.asList("tv-mapper.byPersonTv");
        return super.get(person);
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            output = session.selectList(ConfigFactory.get("tv-mapper.byPersonTv"), person);
//            session.commit();
//        }
//        output.forEach(results::setEncounters);
//        return results;
    }

    @Override
    public Results tvshowByGenreId(Integer genre) {
        mapperList = Arrays.asList("tv-mapper.byGenreTv");
        return super.get(genre);
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            output = session.selectList(ConfigFactory.get("tv-mapper.byGenreTv"), genre);
//            session.commit();
//        }
//        output.forEach(results::setEncounters);
//        return results;
    }

    @Override
    public Results tvshowByLanguageId(Integer lang) {
        mapperList = Arrays.asList("tv-mapper.byLanguageTv");
        return super.get(lang);
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            output = session.selectList(ConfigFactory.get("tv-mapper.byLanguageTv"), lang);
//            session.commit();
//        }
//        output.forEach(results::setEncounters);
//        return results;
    }

    @Override
    public Results tvshowByCountryId(Integer count) {
        mapperList = Arrays.asList("tv-mapper.byCountryTv");
        return super.get(count);
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            output = session.selectList(ConfigFactory.get("tv-mapper.byCountryTv"), count);
//            session.commit();
//        }
//        output.forEach(results::setEncounters);
//        return results;
    }

    @Override
    public Results tvshowByYear(Integer year) {
        mapperList = Arrays.asList("tv-mapper.byYearTv");
        return super.get(year);
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            output = session.selectList(ConfigFactory.get("tv-mapper.byYearTv"), year);
//            session.commit();
//        }
//        output.forEach(results::setEncounters);
//        return results;
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
