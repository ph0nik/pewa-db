package com.pewa.tv;

import com.pewa.anime.Anime;
import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by phonik on 2017-03-31.
 */
public class TvShowDAOImpl implements TvShowDAO {
    private List<TvShowSummary> output = new ArrayList<>();

    private static final Logger log = LogManager.getLogger(TvShowDAOImpl.class);

    @Override
    public void addTvShow(TvShowSummary tvshow) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.BATCH,false)) {
            session.insert("insertTvSummary", tvshow);
            if (!tvshow.getGenres().isEmpty()) {
                session.insert("insertGenreTv", tvshow);
                session.insert("insertGenreBridgeTv", tvshow);
            }
            session.insert("insertTvEpisode", tvshow);
            session.insert("insertPersonTv", tvshow);
            session.insert("insertPersonTvBridge", tvshow);
            session.commit();
        }
    }

    @Override
    public List<TvShowSummary> getTvShow(String query) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            query = new StringBuilder("%")
                    .append(query)
                    .append("%")
                    .toString();
            output = session.selectList("byTitleTv", query);
            for(TvShowSummary item : output) {
                item.setEpisodes(session.selectList("getEpisodes", item.getTvMazeId()));
            }
            session.commit();
        }
        return output;
    }

    @Override
    public List<TvShowSummary> getTvShowById(int id) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            output = session.selectList("byIdTv", id);
            for(TvShowSummary item : output) {
                item.setEpisodes(session.selectList("getEpisodes", item.getTvMazeId()));
            }
            session.commit();
        }
        return output;
    }

    @Override
    public List<TvShowSummary> getTvShowByPerson(String person) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            person = new StringBuilder("%")
                    .append(person)
                    .append("%")
                    .toString();
            output = session.selectList("byPersonTv", person);
            for(TvShowSummary item : output) {
                item.setEpisodes(session.selectList("getEpisodes", item.getTvMazeId()));
            }
            session.commit();
        }
        return output;
    }

    @Override
    public List<TvShowSummary> getTvShowByGenre(String genre) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            genre = new StringBuilder("%")
                    .append(genre)
                    .append("%")
                    .toString();
            output = session.selectList("byGenreTv", genre);
            for(TvShowSummary item : output) {
                item.setEpisodes(session.selectList("getEpisodes", item.getTvMazeId()));
            }
            session.commit();
        }
        return output;
    }

    @Override
    public List<TvShowSummary> getTvShowByYear(String x, String y) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate start, end;
            start = (x.isEmpty()) ? LocalDate.now() : LocalDate.parse(x, formatter);
            end = (y.isEmpty()) ? LocalDate.now() : LocalDate.parse(y, formatter);
            Map<String, LocalDate> map = new HashMap<>();
            if (start.compareTo(end) >= 0) {
                map.put("start", end);
                map.put("end", start);
            } else {
                map.put("start", start);
                map.put("end", end);
            }
            output = session.selectList("byYearTv", map);
            for(TvShowSummary item : output) {
                item.setEpisodes(session.selectList("getEpisodes", item.getTvMazeId()));
            }
            session.commit();
        }
        return output;
    }


}
