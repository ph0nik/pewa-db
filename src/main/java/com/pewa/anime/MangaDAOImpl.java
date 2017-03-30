package com.pewa.anime;

import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MangaDAOImpl implements MangaDAO {
    private List<Manga> output = new ArrayList<>();

    @Override
    public void addManga(Manga manga) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            session.insert("insertManga", manga);
            session.insert("insertPeopleMan", manga);
            session.insert("insertPeopleBridgeMan", manga);
            if (!manga.getGenres().isEmpty()) {
                session.insert("insertGenreMan", manga);
                session.insert("insertGenreBridgeMan", manga);
            }
            session.commit();
        }
    }


    @Override
    public List<Manga> getManga(String query) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            query = new StringBuilder("%")
                    .append(query)
                    .append("%")
                    .toString();
            output = session.selectList("byTitleMan", query);
            session.commit();
        }
        return output;
    }

    @Override
    public List<Manga> getMangaById(int id) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            output = session.selectList("ByIdMan", id);
            session.commit();
        }
        return output;
    }

    @Override
    public List<Manga> getMangaByPerson(String person) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            person = new StringBuilder("%")
                    .append(person)
                    .append("%")
                    .toString();
            output = session.selectList("byPersonMan", person);
            session.commit();
        }
        return output;
    }

    @Override
    public List<Manga> getMangaByGenre(String genre) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            genre = new StringBuilder("%")
                    .append(genre)
                    .append("%")
                    .toString();
            output = session.selectList("byGenreMan", genre);
            session.commit();
        }
        return output;
    }

    @Override
    public List<Manga> getMangaByYear(String x, String y) {
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
            output = session.selectList("byYearMan", map);
            session.commit();
        }
        return output;
    }
}
