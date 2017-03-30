package com.pewa.anime;

import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimeDAOImpl implements AnimeDAO {
    private List<Anime> output = new ArrayList<>();

    public void addAnime(Anime anime) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            session.insert("insertAnime", anime);
            session.insert("insertPeopleAni", anime);
            session.insert("insertPeopleBridgeAni", anime);
            if (!anime.getGenres().isEmpty()) {
                session.insert("insertGenreAni", anime);
                session.insert("insertGenreBridgeAni", anime);
            }
            session.commit();
        }
    }

    public List<Anime> getAnime(String query) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            query = new StringBuilder("%")
                    .append(query)
                    .append("%")
                    .toString();
            output = session.selectList("byTitleAni", query);
            session.commit();
        }
        return output;
    }

    //TODO przerobić obiekt powrotny na Anime
    public List<Anime> getAnimeById(int id) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            output = session.selectList("ByIdAni", id);
            session.commit();
        }
        return output;
    }

    public List<Anime> getAnimeByPerson(String person) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            person = new StringBuilder("%")
                    .append(person)
                    .append("%")
                    .toString();
            output = session.selectList("byPersonAni", person);
            session.commit();
        }
        return output;
    }

    public List<Anime> getAnimeByGenre(String genre) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            genre = new StringBuilder("%")
                    .append(genre)
                    .append("%")
                    .toString();
            output = session.selectList("byGenreAni", genre);
            session.commit();
        }
        return output;
    }

    public List<Anime> getAnimeByYear(String x, String y) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate start, end;
            if (x.isEmpty()) {
                start = LocalDate. now();
            } else {
                start = LocalDate.parse(x, formatter);
            }
            if (y.isEmpty()) {
                end = LocalDate.now();
            } else {
                end = LocalDate.parse(y, formatter);
            }
            Map<String, LocalDate> map = new HashMap<>();
            if (start.compareTo(end) >= 0) {
                map.put("start", end);
                map.put("end", start);
            } else {
                map.put("start", start);
                map.put("end", end);
            }
            output = session.selectList("byYearAni", map);
            session.commit();
        }
        return output;
    }
}
