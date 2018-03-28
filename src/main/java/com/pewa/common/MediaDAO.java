package com.pewa.common;

import com.pewa.MediaModel;
import com.pewa.PewaType;
import com.pewa.book.BookDAO;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import com.pewa.movie.tmdb.Result;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public abstract class MediaDAO {

    public static final String ADD_SUCCESS = " item added  : ";
    public static final String UPDATE_SUCCESS = " item updated : ";
    public static final String DELETE_SUCCESS = " item deleted : ";
    public static final String SEARCH_SUCCESS = " element(s) found";
    public static final String NOTHING_FOUND = " no item with this ID found : ";
    public static final String DUPLICATE_ITEM = " item already in database : ";
    private int rowsAffected;
    private List<Encounter> output;
    private String returnMessage;
    private PewaType type;

    public MediaDAO(PewaType type) {
        this.type = type;
        returnMessage = "";
    }

    private String getType() {
        return type.toString().toLowerCase();
    }

    // All fields of MediaModel object have to be present
    public Results add(MediaModel media, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            for (String mapper : getMapperList()) {
                rowsAffected += session.insert(ConfigFactory.get(mapper), media);
            }
            session.commit();
            results.setRowsAffected(rowsAffected);
            returnMessage = (rowsAffected != 0)
                    ? "[" + getType() + "]" + ADD_SUCCESS + getInfoField()
                    : "[" + getType() + "]" + DUPLICATE_ITEM + getInfoField();
        }
        return results.setReturnMessage(returnMessage);
    }

    public Results delete(Integer id, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            for (String mapper : getMapperList()) {
                rowsAffected += session.delete(ConfigFactory.get(mapper), id);
            }
            session.commit();
            results.setRowsAffected(rowsAffected);
            returnMessage = (rowsAffected != 0)
                    ? "[" + getType() + "]" + DELETE_SUCCESS + getInfoField()
                    : "[" + getType() + "]" + NOTHING_FOUND + getInfoField();
//            log.info(returnMessage);
        }
        return results.setReturnMessage(returnMessage);
    }

    public Results update(MediaModel media, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            for (String mapper : getMapperList()) {
                rowsAffected += session.update(ConfigFactory.get(mapper), media);
            }
            session.commit();
            results.setRowsAffected(rowsAffected);
            returnMessage = (rowsAffected != 0)
                    ? "[" + getType() + "]" + UPDATE_SUCCESS + getInfoField()
                    : "[" + getType() + "]" + NOTHING_FOUND + getInfoField();
        }
        return results.setReturnMessage(returnMessage);
    }

    public Results search(String request, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            String query = new StringBuilder("%")
                    .append(request)
                    .append("%")
                    .toString();
            output = session.selectList(ConfigFactory.get(getMapperList().get(0)), query);
            session.commit();
            output.forEach(x -> x.setType(type));
            output.forEach(results::setEncounters);
            returnMessage = (output.isEmpty())
                    ? "[" + getType() + "]" + NOTHING_FOUND
                    : "[" + getType() + "] " + output.size() + SEARCH_SUCCESS;
        }
        return results.setReturnMessage(returnMessage);
    }

    public Results get(Integer mediaId, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get(getMapperList().get(0)), mediaId);
            session.commit();
            output.forEach(results::setEncounters);
            returnMessage = (output.size() == 0)
                    ? "[" + getType() + "]" + NOTHING_FOUND + mediaId
                    : "[" + getType() + "] " + output.size() + SEARCH_SUCCESS;
        }
        return results.setReturnMessage(returnMessage);
    }

    public Results language(String language, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get(getMapperList().get(0)), language);
            session.commit();
            output.forEach(results::setEncounters);
            returnMessage = (output.size() == 0)
                    ? "[" + getType() + "]" + NOTHING_FOUND
                    : "[" + getType() + "] " + output.size() + SEARCH_SUCCESS;
        }
        return results.setReturnMessage(returnMessage);
    }

    public abstract List<String> getMapperList();

    public abstract String getInfoField();



}
