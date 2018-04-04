package com.pewa.common;

import com.pewa.MediaModel;
import com.pewa.PewaType;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public abstract class MediaDAO {

    private static final String ADD_SUCCESS = "item added  : ";
    private static final String UPDATE_SUCCESS = "item updated : ";
    private static final String DELETE_SUCCESS = "item deleted : ";
    private static final String SEARCH_SUCCESS = "element(s) found";
    private static final String NOTHING_FOUND = "no item with this ID found : ";
    private static final String DUPLICATE_ITEM = "item already in database : ";
    private int rowsAffected;
    private List<MediaModel> output;
    private String returnMessage = "";
    private PewaType type;
    private Results results;

    protected MediaDAO(PewaType type) {
        this.type = type;
    }

    private String getType() {
        return "[" + type.toString().toLowerCase() + "] ";
    }

    // All fields of MediaModel object have to be present
    protected Results add(MediaModel media) {
        results = new Results();
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            for (String mapper : getMapperList()) {
                rowsAffected += session.insert(ConfigFactory.get(mapper), media);
            }
            session.commit();
            results.setRowsAffected(rowsAffected);
            returnMessage = (rowsAffected != 0)
                    ? getType() + ADD_SUCCESS + getInfoField()
                    : getType() + DUPLICATE_ITEM + getInfoField();
        }
        return results.setReturnMessage(returnMessage);
    }

    // Deletes element from database based on given element id
    // multiple scripts are passed if operation needs to be performed on multiple tables
    protected Results delete(Integer id) {
        results = new Results();
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            for (String mapper : getMapperList()) {
                rowsAffected += session.delete(ConfigFactory.get(mapper), id);
            }
            session.commit();
            results.setRowsAffected(rowsAffected);
            returnMessage = (rowsAffected != 0)
                    ? getType() + DELETE_SUCCESS + getInfoField()
                    : getType() + NOTHING_FOUND + getInfoField();
//            log.info(returnMessage);
        }
        return results.setReturnMessage(returnMessage);
    }

    // deletes entries without connections to other tables
    // adds number of deleted rows to exsisting results object
    protected Results delete(Results results) {
        rowsAffected = results.getRowsAffected();
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            for (String mapper : getMapperList()) {
                rowsAffected += session.delete(ConfigFactory.get(mapper));
            }
            session.commit();
            results.setRowsAffected(rowsAffected);
//            returnMessage = (rowsAffected != 0)
//                    ? getType() + DELETE_SUCCESS + getInfoField()
//                    : getType() + NOTHING_FOUND + getInfoField();
        }
        return results;
    }

    // updates element in database, based on model object
    protected Results update(MediaModel media) {
        results = new Results();
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            for (String mapper : getMapperList()) {
                rowsAffected += session.update(ConfigFactory.get(mapper), media);
            }
            session.commit();
            results.setRowsAffected(rowsAffected);
            returnMessage = (rowsAffected != 0)
                    ? getType() + UPDATE_SUCCESS + getInfoField()
                    : getType() + NOTHING_FOUND + getInfoField();
        }
        return results.setReturnMessage(returnMessage);
    }

    protected Results search(String request) {
        results = new Results();
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            String query = new StringBuilder("%")
                    .append(request)
                    .append("%")
                    .toString();
            output = session.selectList(ConfigFactory.get(getMapperList().get(0)), query);
            session.commit();
            output.forEach(results::setEncounters);

            returnMessage = (output.isEmpty())
                    ? getType() + NOTHING_FOUND
                    : getType() + output.size() + SEARCH_SUCCESS;
        }
        return results.setReturnMessage(returnMessage);
    }


    protected Results get(Integer mediaId) {
        results = new Results();
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get(getMapperList().get(0)), mediaId);
            session.commit();
            output.forEach(results::setEncounters);
            returnMessage = (output.size() == 0)
                    ? getType() + NOTHING_FOUND + mediaId
                    : getType() + output.size() + SEARCH_SUCCESS;
        }
        return results.setReturnMessage(returnMessage);
    }

    protected Results language(String language) {
        results = new Results();
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get(getMapperList().get(0)), language);
            session.commit();
            output.forEach(results::setEncounters);
            returnMessage = (output.size() == 0)
                    ? getType() + NOTHING_FOUND
                    : getType() + output.size() + SEARCH_SUCCESS;
        }
        return results.setReturnMessage(returnMessage);
    }

    public abstract List<String> getMapperList();

    public abstract String getInfoField();



}
