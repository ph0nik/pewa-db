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

    private static final Logger log = LogManager.getLogger(TvShowDAOImpl.class);
    private String infoField = "";

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
        return add(tvshow);
    }

    @Override
    public Results updateTvShow(TvShowSummary tvshow) {
        infoField = tvshow.getTitle();
        mapperList = Arrays.asList(
                "tv-mapper.updateTv",
                "tv-mapper.insertTvEpisode"
        );
        return update(tvshow);
    }

    @Override
    public Results deleteTvShow(Integer tvshowid) {
        mapperList = Arrays.asList(
                "tv-mapper.deleteTv",
                "tv-mapper.deleteTvEpisodes"
                );
        return delete(tvshowid);
    }

    @Override
    public Results tvshowByTitle(String query) {
        mapperList = Arrays.asList("tv-mapper.byTitleTv");
        return search(query);
    }

    @Override
    public Results tvshowById(Integer dbId) {
        mapperList = Arrays.asList("tv-mapper.getEpisodes");
        List<MediaModel> episodes = super.get(dbId).getEncounters();
        mapperList = Arrays.asList("tv-mapper.byIdTv");
        Results tvResults = get(dbId);
        for (MediaModel ep : episodes) {
            ((TvShowSummary) tvResults.getEncounters().get(0)).setEpisodes((TvShowEpisode) ep);
        }
        return tvResults;
    }

    @Override
    public Results tvshowByPersonId(Integer person) {
        mapperList = Arrays.asList("tv-mapper.byPersonTv");
        return get(person);
    }

    @Override
    public Results tvshowByGenreId(Integer genre) {
        mapperList = Arrays.asList("tv-mapper.byGenreTv");
        return get(genre);
    }

    @Override
    public Results tvshowByLanguageId(Integer lang) {
        mapperList = Arrays.asList("tv-mapper.byLanguageTv");
        return get(lang);
    }

    @Override
    public Results tvshowByCountryId(Integer count) {
        mapperList = Arrays.asList("tv-mapper.byCountryTv");
        return get(count);
    }

    @Override
    public Results tvshowByYear(Integer year) {
        mapperList = Arrays.asList("tv-mapper.byYearTv");
        return get(year);
    }

    @Override
    public String getInfoField() {
        return infoField;
    }
}
