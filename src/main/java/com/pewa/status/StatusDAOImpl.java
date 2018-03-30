package com.pewa.status;

import com.pewa.MediaModel;
import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import org.apache.ibatis.exceptions.PersistenceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by phonik on 2017-05-30.
 */
@Component
public class StatusDAOImpl implements StatusDAO {

    private static final Logger log = LogManager.getLogger(StatusDAO.class);
    private final String formatterString = "uuuu-MM-dd";
    private final String statusDeleteSucces = "[OK] Status deleted : ";
    private final String statusUpdateSucces = "[OK] Status updated : ";
    private final String statusInsertSucces = "[OK] Status inserted : ";
    private final String statusFailedInfo = "[WARNING] No status found : ";
    private final String duplicateEntryError = "[ERROR] Duplicate entry";
    private List<Status> output;
    private Integer rowsAffected;
    private String statusBridge, statusBridgeUpdate;


    @Override
    public Status setProperStatus(Request request, PewaType pewaType) {
        Status status = request.getStatus();
        status.setEncounterId(request.getExternalId());
        status.setElementType(pewaType);
        return status;
    }

    private void setMapperName(Status status) {
        switch (status.getElementType()) {
            case MOVIE:
                statusBridge = ConfigFactory.get("status-mapper.insertStatusMovieBridge");
                break;
            case TVSERIES:
                statusBridge = ConfigFactory.get("status-mapper.insertStatusTvBridge");
                statusBridgeUpdate = ConfigFactory.get("status-mapper.updateStatusTvBridge");
                break;
            case BOOK:
                statusBridge = ConfigFactory.get("status-mapper.insertStatusBookBridge");
                break;
            case MUSIC:
                statusBridge = "";
                break;
            case ANIME:
                statusBridge = ConfigFactory.get("status-mapper.insertStatusAnimeBridge");
                break;
            case MANGA:
                statusBridge = ConfigFactory.get("status-mapper.insertStatusMangaBridge");
                break;
        }
    }

    @Override
    public Results addStatus(Status status, Results results) {
        rowsAffected = results.getRowsAffected();
        setMapperName(status);
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.insert(ConfigFactory.get("status-mapper.insertStatus"), status);
            int n = session.insert(statusBridge, status);
            if (n == 0) {
                session.rollback();
            } else {
                rowsAffected += n;
                session.commit();
            }
            results.setRowsAffected(rowsAffected);
            if (rowsAffected != 0 && n != 0)
                results.setReturnMessage(statusInsertSucces + status.getElementType() + " | " + status);
            else results.setReturnMessage(statusFailedInfo + status.getElementType() + " | " + status);
        }
        return results;
    }

    @Override
    public Results updateStatus(Status status, Results results) {
        rowsAffected = 0;
        setMapperName(status);
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.update(ConfigFactory.get("status-mapper.updateStatus"), status);
            if (status.getElementType() == PewaType.TVSERIES) {
                rowsAffected += session.update(statusBridgeUpdate, status);
            }
            session.commit();
            results.setRowsAffected(rowsAffected);
            if (rowsAffected != 0) results.setReturnMessage(statusUpdateSucces + status);
            else results.setReturnMessage(statusFailedInfo + status);
        } catch (PersistenceException ex) {
            /*final Throwable cause = ex.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                log.debug(ex);
            }*/
            String error = ex.getCause().getMessage();
            if (error.contains("Duplicate entry")) {
                results.setReturnMessage(duplicateEntryError);
            } else {
                log.debug(ex.getMessage());
            }
        }
        return results;
    }

    @Override
    public Results deleteStatus(Integer statusId, Results results) {
        rowsAffected = 0;
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            rowsAffected += session.delete(ConfigFactory.get("status-mapper.deleteStatus"), statusId);
            session.commit();
            results.setRowsAffected(rowsAffected);
            if (rowsAffected != 0) results.setReturnMessage(statusDeleteSucces + statusId);
            else results.setReturnMessage(statusFailedInfo + statusId);
        }
        return results;
    }

    @Override
    public Results getStatusByNumber(Integer numberOfStatuses, Results results) {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("status-mapper.getLatestStatus"), numberOfStatuses);
            Map<PewaType, Set<Integer>> encouterIdsGrouped = output.stream()
                    .collect(Collectors.groupingBy(Status::getElementType, Collectors.mapping(Status::getEncounterId, Collectors.toSet())));
            Set<Integer> encounterIdList;
            List<MediaModel> encountersOutput;
            log.info(results.getResultsFound());

            encounterIdList = encouterIdsGrouped.get(PewaType.TVSERIES);
            if (encounterIdList != null) {
                encountersOutput = session.selectList(ConfigFactory.get("tv-mapper.byExternalIdTv"), encounterIdList);
//                encountersOutput.forEach(t -> t.setType(PewaType.TVSERIES));
                encountersOutput.forEach(results::setEncounters);
                log.info("tv size: " + encountersOutput.size());
                log.info("result tv: " + results.getResultsFound());
            }
            encounterIdList = encouterIdsGrouped.get(PewaType.MOVIE);
            if (encounterIdList != null) {
                encountersOutput = session.selectList(ConfigFactory.get("movie-mapper.byExternalIdMovie"), encounterIdList);
                encountersOutput.forEach(results::setEncounters);
                log.info("movie size: " + encountersOutput.size());
                log.info("result movie: " + results.getResultsFound());
            }
            encounterIdList = encouterIdsGrouped.get(PewaType.ANIME);
            if (encounterIdList != null) {
                encountersOutput = session.selectList(ConfigFactory.get("anime-mapper.byExternalIdAni"), encounterIdList);
//                encountersOutput.forEach(t -> t.setType(PewaType.ANIME));
                encountersOutput.forEach(results::setEncounters);
                log.info("anime size: " + encountersOutput.size());
                log.info("result anime: " + results.getResultsFound());
            }
            encounterIdList = encouterIdsGrouped.get(PewaType.MANGA);
            if (encounterIdList != null) {
                encountersOutput = session.selectList(ConfigFactory.get("manga-mapper.byExternalIdMan"), encounterIdList);
//                encountersOutput.forEach(t -> t.setType(PewaType.MANGA));
                encountersOutput.forEach(results::setEncounters);
                log.info("manga size: " + encountersOutput.size());
                log.info("result manga: " + results.getResultsFound());
            }
            encounterIdList = encouterIdsGrouped.get(PewaType.BOOK);
            if (encounterIdList != null) {
                encountersOutput = session.selectList(ConfigFactory.get("book-mapper.byExternalIdBook"), encounterIdList);
//                encountersOutput.forEach(t -> t.setType(PewaType.BOOK));
                encountersOutput.forEach(results::setEncounters);
                log.info("bok size: " + encountersOutput.size());
                log.info("result book: " + results.getResultsFound());
            }
        }
        return results;
    }

    public Results getStatusByDateRange(Request request, Results results) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterString);
        DateRange dateRange = new DateRange();
        LocalDate date1 = LocalDate.parse(request.getDateInString(), formatter);
        LocalDate date2 = LocalDate.parse(request.getDateOutString(), formatter);
        if (date1.isBefore(date2)) {
            dateRange.setDate1(date1);
            dateRange.setDate2(date2);
        } else {
            dateRange.setDate1(date2);
            dateRange.setDate2(date1);
        }
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
            output = session.selectList(ConfigFactory.get("status-mapper.getLatestStatus"), dateRange);

            Map<PewaType, Set<Integer>> encouterIdsGrouped = output.stream()
                    .collect(Collectors.groupingBy(Status::getElementType, Collectors.mapping(Status::getEncounterId, Collectors.toSet())));
            Set<Integer> encounterIdList;
            List<MediaModel> encountersOutput;

            encounterIdList = encouterIdsGrouped.get(PewaType.TVSERIES);
            if (encounterIdList != null) {
                encountersOutput = session.selectList(ConfigFactory.get("tv-mapper.byExternalIdTv"), encounterIdList);
                encountersOutput.forEach(results::setEncounters);
            }
            encounterIdList = encouterIdsGrouped.get(PewaType.MOVIE);
            if (encounterIdList != null) {
                encountersOutput = session.selectList(ConfigFactory.get("movie-mapper.byExternalIdMovie"), encounterIdList);
                encountersOutput.forEach(results::setEncounters);
            }
            encounterIdList = encouterIdsGrouped.get(PewaType.ANIME);
            if (encounterIdList != null) {
                encountersOutput = session.selectList(ConfigFactory.get("anime-mapper.byExternalIdAni"), encounterIdList);
                encountersOutput.forEach(results::setEncounters);
            }
            encounterIdList = encouterIdsGrouped.get(PewaType.MANGA);
            if (encounterIdList != null) {
                encountersOutput = session.selectList(ConfigFactory.get("manga-mapper.byExternalIdMan"), encounterIdList);
                encountersOutput.forEach(results::setEncounters);
            }
            encounterIdList = encouterIdsGrouped.get(PewaType.BOOK);
            if (encounterIdList != null) {
                encountersOutput = session.selectList(ConfigFactory.get("book-mapper.byExternalIdBook"), encounterIdList);
                encountersOutput.forEach(results::setEncounters);
            }
        }
        return results;
    }
}
