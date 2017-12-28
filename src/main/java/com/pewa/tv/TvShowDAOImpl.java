package com.pewa.tv;

import com.pewa.PewaType;
import com.pewa.common.Encounter;
import com.pewa.common.PewaSelect;
import com.pewa.common.Results;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phonik on 2017-03-31.
 */
@Component
public class TvShowDAOImpl implements TvShowDAO {
    private List<Encounter> output = new ArrayList<>();

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

    @Override
    public Results addTvShow(TvShowSummary tvshow, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertTvSummary"), tvshow);
            if (!tvshow.getGenres().isEmpty()) {
                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertGenreTv"), tvshow);
                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertGenreBridgeTv"), tvshow);
            }
            if (!tvshow.getEpisodes().isEmpty()) {
                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertTvEpisode"), tvshow);
            }
            if (!tvshow.getStaff().isEmpty()) {
                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertPersonTv"), tvshow);
                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertPersonTvBridge"), tvshow);
            }
            rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertLanguageTv"), tvshow);
            rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertLanguageBridgeTv"), tvshow);
            rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertCountryTv"), tvshow);
            rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertCountryBridgeTv"), tvshow);
            session.commit();
            results.setRowsAffected(rowsAffected);
            if (rowsAffected != 0)
                results.setReturnMessage(addSuccess + tvshow.getTitle() + " " + tvshow.getPremiered().getYear());
            else results.setReturnMessage(alreadyInDb + tvshow.getTitle() + " " + tvshow.getPremiered().getYear());
        }
        return results;
    }

    @Override
    public Results updateTvShow(TvShowSummary tvshow, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.update(ConfigFactory.get("tv-mapper.updateTv"), tvshow);
            if (!tvshow.getEpisodes().isEmpty()) {
                rowsAffected += session.insert(ConfigFactory.get("tv-mapper.insertTvEpisode"), tvshow);
            }
            session.commit();
        }
        results.setRowsAffected(rowsAffected);
        if (rowsAffected != 0) results.setReturnMessage(updateSuccess + tvshow.getTitle() + " " + tvshow.getPremiered().getYear());
        else results.setReturnMessage(nothingFound + tvshow.getTitle() + " " + tvshow.getPremiered().getYear());
        return results;
    }

    @Override
    public Results deleteTvShow(Integer tvshowid, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.delete(ConfigFactory.get("tv-mapper.deleteTv"), tvshowid);
            rowsAffected += session.delete(ConfigFactory.get("tv-mapper.deleteTvEpisodes"), tvshowid);
            session.commit();
            results.setRowsAffected(rowsAffected);
        }
        if (rowsAffected != 0) results.setReturnMessage(deleteSuccess + tvshowid);
        else results.setReturnMessage(nothingFound + tvshowid);
        return results;
    }

    @Override
    public Results tvshowByTitle(String query, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            query = new StringBuilder("%")
                    .append(query)
                    .append("%")
                    .toString();
            output = session.selectList(ConfigFactory.get("tv-mapper.byTitleTv"), query);
            /*output.forEach(item -> session.selectList("getEpisodes", item.getTvMazeId()));
            for(TvShowSummary item : output) {
                item.setEpisodes(session.selectList("getEpisodes", item.getTvMazeId()));
            }*/
            session.commit();
        }
        output.forEach(x -> x.setType(tvType));
        output.forEach(results::setEncounters);
        return results;
    }

    public Results getObjectById(PewaSelect id, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList("getByElementId", id);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public TvShowSummary getTvShowByExternalId(Integer id) {
        return null;
    }

    @Override
    public Results tvshowById(Integer dbId, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            tvShowSummary = session.selectOne(ConfigFactory.get("tv-mapper.byIdTv"), dbId);
            List<TvShowEpisode> episodes = session.selectList("getEpisodes", dbId);
            tvShowSummary.setEpisodes(episodes);
            session.commit();
            results.setEncounters(tvShowSummary);
        }
        return results;
    }

    @Override
    public Results tvshowByPersonId(Integer person, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("tv-mapper.byPersonTv"), person);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results tvshowByGenreId(Integer genre, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("tv-mapper.byGenreTv"), genre);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results tvshowByLanguageId(Integer lang, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("tv-mapper.byLanguageTv"), lang);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results tvshowByCountryId(Integer count, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("tv-mapper.byCountryTv"), count);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results tvshowByYear(Integer year, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("tv-mapper.byYearTv"), year);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }


}
