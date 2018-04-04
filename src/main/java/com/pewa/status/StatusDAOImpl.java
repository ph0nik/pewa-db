package com.pewa.status;

import com.pewa.InitAllTables;
import com.pewa.MediaModel;
import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import com.pewa.request.StatusRequest;
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
public class StatusDAOImpl extends MediaDAO implements StatusDAO {

    private static final Logger log = LogManager.getLogger(StatusDAO.class);
    private final String formatterString = "uuuu-MM-dd";
    private final String statusDeleteSucces = "[OK] Status deleted : ";
    private final String statusUpdateSucces = "[OK] Status updated : ";
    private final String statusInsertSucces = "[OK] Status inserted : ";
    private final String statusFailedInfo = "[WARNING] No status found : ";
    private final String duplicateEntryError = "[ERROR] Duplicate entry";
    private final String missingParameters = "Missing parameters: ";
    private List<Status> output;
    private Integer rowsAffected;
    private String statusBridge, statusBridgeUpdate;
    private List<String> mapperList = new ArrayList<>();
    private String infoField = "";
    private InitAllTables tablesManagement;


    public StatusDAOImpl() {
        super(PewaType.STATUS);
        tablesManagement = new InitAllTables(PewaType.STATUS);
    }

    @Override
    public Status setProperStatus(Request request, PewaType pewaType) {
        Status status = request.getStatus();
        status.setEncounterId(request.getExternalId());
        status.setElementType(pewaType);
        return status;
    }

    private void setMapperName(PewaType statusType) {
        switch (statusType) {
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

    //TODO check why is rollback implementet within this method
    //
    @Override
    public Results addStatus(Status status) {
        if (!status.isEmpty()) {
            setMapperName(status.getElementType());
            mapperList = Arrays.asList("status-mapper.insertStatus", statusBridge);
            return super.add(status);
        } else {
            Results results = new Results();
            return results.setReturnMessage(missingParameters + status.getMissingParameters().toString());
        }


//        rowsAffected = results.getRowsAffected();
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            rowsAffected += session.insert(ConfigFactory.get("status-mapper.insertStatus"), status);
//            int n = session.insert(statusBridge, status);
//            if (n == 0) {
//                session.rollback();
//            } else {
//                rowsAffected += n;
//                session.commit();
//            }
//            results.setRowsAffected(rowsAffected);
//            if (rowsAffected != 0 && n != 0)
//                results.setReturnMessage(statusInsertSucces + status.getElementType() + " | " + status);
//            else results.setReturnMessage(statusFailedInfo + status.getElementType() + " | " + status);
//        }
//        return results;
    }

    @Override
    public Results updateStatus(Status status) {
        if (!status.isEmpty()) {
            setMapperName(status.getElementType());
            mapperList = Arrays.asList("status-mapper.updateStatus");
            if (status.getElementType() == PewaType.TVSERIES) {
                mapperList.add(statusBridgeUpdate);
            }
            return super.update(status);
        } else {
            Results results = new Results();
            return results.setReturnMessage(missingParameters + status.getMissingParameters().toString());
        }
//        rowsAffected = 0;
//        setMapperName(status.getElementType());
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            rowsAffected += session.update(ConfigFactory.get("status-mapper.updateStatus"), status);
//            if (status.getElementType() == PewaType.TVSERIES) {
//                rowsAffected += session.update(statusBridgeUpdate, status);
//            }
//            session.commit();
//            results.setRowsAffected(rowsAffected);
//            if (rowsAffected != 0) results.setReturnMessage(statusUpdateSucces + status);
//            else results.setReturnMessage(statusFailedInfo + status);
//        } catch (PersistenceException ex) {
//            /*final Throwable cause = ex.getCause();
//            if (cause instanceof MySQLIntegrityConstraintViolationException) {
//                log.debug(ex);
//            }*/
//            String error = ex.getCause().getMessage();
//            if (error.contains("Duplicate entry")) {
//                results.setReturnMessage(duplicateEntryError);
//            } else {
//                log.debug(ex.getMessage());
//            }
//        }
//        return results;
    }

    // Deletes status with given id, deletes all related entries with no connections left
    @Override
    public Results deleteStatus(Integer statusId) {
        mapperList = Arrays.asList("status-mapper.deleteStatus");
        Results delete = super.delete(statusId);
        return tablesManagement.cleanAll(delete);
//        rowsAffected = 0;
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            rowsAffected += session.delete(ConfigFactory.get("status-mapper.deleteStatus"), statusId);
//            session.commit();
//            results.setRowsAffected(rowsAffected);
//            if (rowsAffected != 0) results.setReturnMessage(statusDeleteSucces + statusId);
//            else results.setReturnMessage(statusFailedInfo + statusId);
//        }
//        return results;
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


    @Override
    public List<String> getMapperList() {
        return null;
    }

    @Override
    public String getInfoField() {
        return null;
    }
}
