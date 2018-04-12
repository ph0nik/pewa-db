package com.pewa.common;

import com.pewa.InitAllTables;
import com.pewa.MediaModel;
import com.pewa.PewaType;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import com.pewa.exceptions.EmptyMapperException;
import org.apache.ibatis.exceptions.IbatisException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractMediaDAO {

    private static final Logger log = LogManager.getLogger(AbstractMediaDAO.class);
    private static final String ADD_SUCCESS = " item added  : ";
    private static final String UPDATE_SUCCESS = " item updated : ";
    private static final String DELETE_SUCCESS = " item deleted : ";
    private static final String SEARCH_SUCCESS = " element(s) found";
    private static final String NOTHING_FOUND = " no item with this ID found : ";
    private static final String DUPLICATE_ITEM = " item already in database : ";
    private static final String EMPTY_MAPPER = " mapper list is empty";
    private static final String INTERNAL_ERROR = " Something unexpected happened, contact developer";

    private int rowsAffected;
    private List<MediaModel> output;
    private String returnMessage = "";
    private PewaType type;
    private Results results;
    protected InitAllTables tableManagement;

    protected AbstractMediaDAO(PewaType type) {
        this.type = type;
    }

    protected InitAllTables getTablesManagement() {
        return this.tableManagement;
    }

    private String getType() {
        return "[" + type.toString().toLowerCase() + "] ";
    }

    // All fields of MediaModel object have to be present
    protected Results add(MediaModel media) {
        results = new Results();
        rowsAffected = 0;
        if (!getMapperList().isEmpty()) {
            try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
                for (String mapper : getMapperList()) {
                    rowsAffected += session.insert(ConfigFactory.get(mapper), media);
                }
                session.commit();
                results.setRowsAffected(rowsAffected);
                returnMessage = (rowsAffected != 0)
                        ? getType() + ADD_SUCCESS + getInfoField()
                        : getType() + DUPLICATE_ITEM + getInfoField();
            } catch (IbatisException ex) {
                log.error(ex.getMessage());
                returnMessage = INTERNAL_ERROR;
            }
        } else {
            returnMessage = EMPTY_MAPPER;
        }

        return results.setReturnMessage(returnMessage);
    }

    // Deletes element from database based on given element id
    // multiple scripts are passed if operation needs to be performed on multiple tables
    protected Results delete(Integer id) {
        results = new Results();
        rowsAffected = 0;
        if (!getMapperList().isEmpty()) {
            try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
                for (String mapper : getMapperList()) {
                    rowsAffected += session.delete(ConfigFactory.get(mapper), id);
                }
                session.commit();
                results.setRowsAffected(rowsAffected);
                returnMessage = (rowsAffected != 0)
                        ? getType() + DELETE_SUCCESS + getInfoField()
                        : getType() + NOTHING_FOUND + getInfoField();
            } catch (IbatisException ex) {
                log.error(ex.getMessage());
                returnMessage = INTERNAL_ERROR;
            }
        } else {
            returnMessage = EMPTY_MAPPER;
        }
        return results.setReturnMessage(returnMessage);
    }

    // deletes entries without connections to other tables
    // adds number of deleted rows to exsisting results object
    protected Results delete(Results results) {
        rowsAffected = results.getRowsAffected();
        if (!getMapperList().isEmpty()) {
            try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
                for (String mapper : getMapperList()) {
                    rowsAffected += session.delete(ConfigFactory.get(mapper));
                }
                session.commit();
                results.setRowsAffected(rowsAffected);
                returnMessage = (rowsAffected != 0)
                        ? getType() + DELETE_SUCCESS + getInfoField()
                        : getType() + NOTHING_FOUND + getInfoField();
            } catch (IbatisException ex) {
                log.error(ex.getMessage());
                returnMessage = INTERNAL_ERROR;
            }
        } else {
            returnMessage = EMPTY_MAPPER;
        }
        return results;
    }

    // updates element in database, based on model object
    protected Results update(MediaModel media) {
        results = new Results();
        rowsAffected = 0;
        if (!getMapperList().isEmpty()) {
            try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
                for (String mapper : getMapperList()) {
                    rowsAffected += session.update(ConfigFactory.get(mapper), media);
                }
                session.commit();
                results.setRowsAffected(rowsAffected);
                returnMessage = (rowsAffected != 0)
                        ? getType() + UPDATE_SUCCESS + getInfoField()
                        : getType() + NOTHING_FOUND + getInfoField();
            } catch (IbatisException ex) {
                log.error(ex.getMessage());
                returnMessage = INTERNAL_ERROR;
            }
        } else {
            returnMessage = EMPTY_MAPPER;
        }
        return results.setReturnMessage(returnMessage);
    }

    protected Results search(String request) {
        results = new Results();
        if (!getMapperList().isEmpty()) {
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
            } catch (IbatisException ex) {
                log.error(ex.getMessage());
                returnMessage = INTERNAL_ERROR;
            }
        } else {
            returnMessage = EMPTY_MAPPER;
        }
        return results.setReturnMessage(returnMessage);
    }


    protected Results get(Integer mediaId) {
        results = new Results();
        if (!getMapperList().isEmpty()) {
            try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
                output = session.selectList(ConfigFactory.get(getMapperList().get(0)), mediaId);
                session.commit();
                output.forEach(results::setEncounters);
                returnMessage = (output.size() == 0)
                        ? getType() + NOTHING_FOUND + mediaId
                        : getType() + output.size() + SEARCH_SUCCESS;
            } catch (IbatisException ex) {
                log.error(ex.getMessage());
                returnMessage = INTERNAL_ERROR;
            }
        } else {
            returnMessage = EMPTY_MAPPER;
        }
        return results.setReturnMessage(returnMessage);
    }

    protected Results language(String language) {
        results = new Results();
        if (!getMapperList().isEmpty()) {
            try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
                output = session.selectList(ConfigFactory.get(getMapperList().get(0)), language);
                session.commit();
                output.forEach(results::setEncounters);
                returnMessage = (output.size() == 0)
                        ? getType() + NOTHING_FOUND
                        : getType() + output.size() + SEARCH_SUCCESS;
            } catch (IbatisException ex) {
                log.error(ex.getMessage());
                returnMessage = INTERNAL_ERROR;
            }
        } else {
            returnMessage = EMPTY_MAPPER;
        }
        return results.setReturnMessage(returnMessage);
    }

    public abstract List<String> getMapperList();

    public abstract String getInfoField();


}
