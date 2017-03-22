package com.pewa.book;

import com.pewa.MyBatisFactory;
import com.pewa.config.ConfigReader;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookDAOImpl implements BookDAO {

    private String dbName = ConfigReader.dbName;
    private String login = ConfigReader.userName;
    private String pass = ConfigReader.userPass;
    private String dataBaseUrl = ConfigReader.dbUrlUser.concat(dbName);
    private String dataBaseUrlSslOff = dataBaseUrl.concat("?useSSL=false");
    private List<Book> output = new ArrayList<>();

    private static final Logger logger = LogManager.getLogger(BookDAOImpl.class);

    public void initBook() {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            session.update("createBookTable");
            session.update("createPersonTable");
            session.update("createGenreTable");
            session.update("createFormTable");
            session.commit();
        } finally {
            session.close();
        }
    }

    public void addBook(Book bookInfo) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
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
        } finally {
            session.close();
        }
        //session.rollback();
    }

    public List<Book> getBook(String query) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            query = new StringBuilder("%")
                    .append(query)
                    .append("%")
                    .toString();
            output = session.selectList("byTitle", query);
            session.commit();
        } finally {
            session.close();
        }
        return output;
    }

    public List<Book> getBookById(String id) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            output = session.selectList("ById", id);
            session.commit();
        } finally {
            session.close();
        }
        return output;
    }

    public List<Book> booksByPerson(String person) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            person = new StringBuilder("%")
                    .append(person)
                    .append("%")
                    .toString();
            output = session.selectList("byPerson", person);
            session.commit();
        } finally {
            session.close();
        }
        return output;
    }

    public List<Book> booksByGenre(String genre) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            genre = new StringBuilder("%")
                    .append(genre)
                    .append("%")
                    .toString();
            output = session.selectList("byGenre", genre);
            session.commit();
        } finally {
            session.close();
        }
        return output;
    }

    public List<Book> booksByLanguage(String language) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
            language = new StringBuilder("%")
                    .append(language)
                    .append("%")
                    .toString();
            output = session.selectList("byLanguage", language);
            session.commit();
        } finally {
            session.close();
        }
        return output;
    }

    public List<Book> booksByYear(int x, int y) {
        SqlSession session = MyBatisFactory.connectionUser().openSession(false);
        try {
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
        } finally {
            session.close();
        }
        return output;
    }
}
