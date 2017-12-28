package com.pewa.book;

import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAOImpl implements BookDAO {

    public static final Logger log = LogManager.getLogger(BookDAOImpl.class);
    private static final String addSuccess = "Book item added  : ";
    private static final String updateSuccess = "Book item updated : ";
    private static final String deleteSuccess = "Book item deleted : ";
    private static final String nothingFound = "No book with this Id found : ";
    private static final String alreadyInDb = "Book item already in database : ";
    private static final String formatterString = "uuuu-MM-dd";
    private final PewaType bookType = PewaType.BOOK;
    private List<Encounter> output;
    private Integer rowsAffected;

    public void initBook() {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            session.update("createBookTable");
            session.update("createPersonTable");
            session.update("createGenreTable");
            session.update("createFormTable");
            session.commit();
        }
    }

    @Override
    public Results addBook(Book book, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.insert(ConfigFactory.get("book-mapper.insertBook"), book);
            rowsAffected += session.insert(ConfigFactory.get("book-mapper.insertPeopleBoo"), book);
            rowsAffected += session.insert(ConfigFactory.get("book-mapper.insertPeopleBridgeBoo"), book);
            if (!book.getGenre().isEmpty()) {
                rowsAffected += session.insert(ConfigFactory.get("book-mapper.insertGenreBoo"), book);
                rowsAffected += session.insert(ConfigFactory.get("book-mapper.insertGenreBridgeBoo"), book);
            }
            if (!book.getForm().isEmpty()) {
                rowsAffected += session.insert(ConfigFactory.get("book-mapper.insertForm"), book);
            }
            session.commit();
            results.setRowsAffected(rowsAffected);
            if (rowsAffected != 0) results.setReturnMessage(addSuccess + book.getOriginalTitle());
            else results.setReturnMessage(alreadyInDb + book.getOriginalTitle());
        }
        return results;
    }

    @Override
    public Results delBook(Integer bookid, Results results) {
    rowsAffected = 0;
    try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
        rowsAffected += session.delete(ConfigFactory.get("book-mapper.deleteBook"), bookid);
        session.commit();
        results.setRowsAffected(rowsAffected);
        }
        if (rowsAffected != 0) results.setReturnMessage(deleteSuccess + bookid);
        else  results.setReturnMessage(nothingFound + bookid);
        return results;
    }

    @Override
    public Results udpateBook(Book book, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.update(ConfigFactory.get("book-mapper.updateBook"), book);
            session.commit();
            results.setRowsAffected(rowsAffected);
        }
        if (rowsAffected !=0) results.setReturnMessage(updateSuccess + book.getOriginalTitle());
        else results.setReturnMessage(nothingFound + book.getOriginalTitle());
        return results;
    }

    @Override
    public Results getBook(String request, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            String query = new StringBuilder("%")
                    .append(request)
                    .append("%")
                    .toString();
            output = session.selectList(ConfigFactory.get("book-mapper.byTitle"), query);
            session.commit();
        }
        output.forEach(x -> x.setType(bookType));
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results getBookById(Integer bookId, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("book-mapper.ById"), bookId);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results booksByPerson(Integer personId, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("book-mapper.byPerson"), personId);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results booksByGenre(Integer genreId, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("book-mapper.byGenre"), genreId);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results booksByLanguage(String language, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("book-mapper.byLanguage"), language);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Results booksByYear(Request date, Results results) {
        Integer year = date.getYear();
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("book-mapper.byYear"), year);
            session.commit();
        }
        output.forEach(results::setEncounters);
        return results;
    }

}
