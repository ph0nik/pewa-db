package com.pewa.anime;

import com.pewa.common.Genre;
import com.pewa.common.Person;
import com.pewa.common.Results;
import com.pewa.dao.MyBatisFactory;
import com.pewa.dao.NewDataBase;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnimeDAOImpl implements AnimeDAO {
    private List<Anime> output = new ArrayList<>();

    public void addAnime(Anime anime) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.BATCH,false)) {
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

    @Override
    public Results getAnime(String query, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            query = new StringBuilder("%")
                    .append(query)
                    .append("%")
                    .toString();
            output = session.selectList("byTitleAni", query);
            session.commit();
        }
        return results.setAnimes(output);
    }

    //TODO przerobić obiekt powrotny na Anime
    public Anime getAnimeById(Integer id) {
        Anime anime = new Anime();
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            anime = session.selectOne("ByIdAni", id);
            session.commit();
        }
        return anime;
    }

    @Override
    public Results getAnimeByPerson(Person person, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            output = session.selectList("byPersonAni", person.getId());
            session.commit();
        }
        return results.setAnimes(output);
    }

    @Override
    public Results getAnimeByGenre(Genre genre, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            output = session.selectList("byGenreAni", genre.getId());
            session.commit();
        }
        return results.setAnimes(output);
    }

    @Override
    public Results getAnimeByYear(String x, String y, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate start, end;
            if (x.isEmpty()) {
                start = LocalDate.now();
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
        return results.setAnimes(output);
    }

}
