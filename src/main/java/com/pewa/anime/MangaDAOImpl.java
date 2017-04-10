package com.pewa.anime;

import com.pewa.common.Genre;
import com.pewa.common.Person;
import com.pewa.common.Results;
import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MangaDAOImpl implements MangaDAO {
    private List<Manga> output = new ArrayList<>();
    private Integer countRows = 0;

    @Override
    public Results addManga(Manga manga, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            countRows =+ session.insert("insertManga", manga);
            countRows =+ session.insert("insertPeopleMan", manga);
            countRows =+ session.insert("insertPeopleBridgeMan", manga);
            if (!manga.getGenres().isEmpty()) {
                countRows =+ session.insert("insertGenreMan", manga);
                countRows =+ session.insert("insertGenreBridgeMan", manga);
            }
            session.commit();

        }
        return results.setRowsAffected(countRows);
    }


    @Override
    public Results getManga(String query, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            query = new StringBuilder("%")
                    .append(query)
                    .append("%")
                    .toString();
            output = session.selectList("byTitleMan", query);
            session.commit();
        }
        return results.setMangas(output);
    }

    @Override
    public Results getMangaById(int id, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            output = session.selectList("ByIdMan", id);
            session.commit();
        }
        return results.setMangas(output);
    }

    @Override
    public Results getMangaByPerson(Person person, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            output = session.selectList("byPersonMan", person.getId());
            session.commit();
        }
        return results.setMangas(output);
    }

    @Override
    public Results getMangaByGenre(Genre genre, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            output = session.selectList("byGenreMan", genre.getId());
            session.commit();
        }
        return results.setMangas(output);
    }

    @Override
    public Results getMangaByYear(String x, String y, Results results) {
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
        return results.setMangas(output);
    }
}
