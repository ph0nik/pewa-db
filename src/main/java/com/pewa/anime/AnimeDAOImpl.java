package com.pewa.anime;

import com.pewa.MyBatisFactory;
import com.pewa.book.Book;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimeDAOImpl implements AnimeDAO {
    private List<Anime> output = new ArrayList<>();

    @Override
    public void addAnime(Anime anime) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            session.insert("insertAnime", anime);
            session.insert("insertPeopleAni", anime);
            session.insert("insertPeopleBridgeAni", anime);
            if (!anime.getGenres().isEmpty()) {
                session.insert("insertGenreAni", anime);
                session.insert("insertGenreBridgeAni", anime);
            }
            session.commit();
        } finally {
            session.close();
        }
    }

    public List<Anime> getAnime(String query) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            query = new StringBuilder("%")
                    .append(query)
                    .append("%")
                    .toString();
            output = session.selectList("byTitleAni", query);
            session.commit();
        } finally {
            session.close();
        }
        return output;
    }

    public List<Anime> getAnimeById(int id) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            output = session.selectList("ByIdAni", id);
            session.commit();
        } finally {
            session.close();
        }
        return output;
    }

    public List<Anime> animeByPerson(String person) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            person = new StringBuilder("%")
                    .append(person)
                    .append("%")
                    .toString();
            output = session.selectList("byPersonAni", person);
            session.commit();
        } finally {
            session.close();
        }
        return output;
    }

    public List<Anime> animeByGenre(String genre) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            genre = new StringBuilder("%")
                    .append(genre)
                    .append("%")
                    .toString();
            output = session.selectList("byGenreAni", genre);
            session.commit();
        } finally {
            session.close();
        }
        return output;
    }

    public List<Anime> animeByYear(String x, String y) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate start = LocalDate.parse(x, formatter);
            LocalDate end = LocalDate.parse(y, formatter);
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
        } finally {
            session.close();
        }
        return output;
    }
}
