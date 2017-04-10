package com.pewa.book;

import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookDAOImpl implements BookDAO {

    public static final Logger log = LogManager.getLogger(BookDAOImpl.class);

    private List<Book> output = new ArrayList<>();

/*    public void initBook() {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            session.update("createBookTable");
            session.update("createPersonTable");
            session.update("createGenreTable");
            session.update("createFormTable");
            session.commit();
        }
    }*/

    public void addBook(Book bookInfo) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            session.insert("insertBook", bookInfo);
            session.insert("insertPeopleBoo", bookInfo);
            session.insert("insertPeopleBridgeBoo", bookInfo);
            if (!bookInfo.getGenre().isEmpty()) {
                session.insert("insertGenreBoo", bookInfo);
                session.insert("insertGenreBridgeBoo", bookInfo);
            }
            if (!bookInfo.getForm().isEmpty()) {
                session.insert("insertForm", bookInfo);
            }
            session.commit();
        }
        //session.rollback();
    }

    public List<Book> getBook(String query) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            query = new StringBuilder("%")
                    .append(query)
                    .append("%")
                    .toString();
            output = session.selectList("byTitle", query);
            session.commit();
        }
        return output;
    }

    public List<Book> getBookById(int id) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            output = session.selectList("ById", id);
            session.commit();
        }
        return output;
    }

    public List<Book> booksByPerson(String person) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            person = new StringBuilder("%")
                    .append(person)
                    .append("%")
                    .toString();
            output = session.selectList("byPerson", person);
            session.commit();
        }
        return output;
    }

    public List<Book> booksByGenre(String genre) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            genre = new StringBuilder("%")
                    .append(genre)
                    .append("%")
                    .toString();
            output = session.selectList("byGenre", genre);
            session.commit();
        }
        return output;
    }

    public List<Book> booksByLanguage(String language) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            language = new StringBuilder("%")
                    .append(language)
                    .append("%")
                    .toString();
            output = session.selectList("byLanguage", language);
            session.commit();
        }
        return output;
    }

    public List<Book> booksByYear(int x, int y) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            Map<String, Integer> map = new HashMap<>();
            if (x >= y) {
                map.put("start", y);
                map.put("end", x);
            } else {
                map.put("start", x);
                map.put("end", y);
            }
            output = session.selectList("byYear", map);
            session.commit();
        }
        return output;
    }
}
