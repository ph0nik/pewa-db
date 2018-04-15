package com.pewa.status;



import com.pewa.MediaSource;
import com.pewa.PewaType;
import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.request.StatusRequest;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by phonik on 2017-05-31.
 */
class StatusDAOImplTest {

    private StatusDAO statusDAO;
    private Results results;
    private Status status;

    @BeforeEach
    public void init() {
        statusDAO = new StatusDAOImpl();
        results = new Results();
        status = new Status();
    }

    @Disabled
    @Test
    public void checkForEmptyStatusSingleField() {
        status.setElementType(PewaType.ANIME);
        status.setEncounterId(1);
        status.setEncounterRating(8);
        status.setComment("comment");
        status.setEncounterDate(null);
        status.setMediaSource(MediaSource.EBOOK);
        status.setSeason(2);
        status.setStatusId(1);
        status.setAddedDate(LocalDateTime.now());
        boolean empty = status.isEmpty();
        assertEquals(empty, true);
        System.out.println("Status is empty: " + empty);
        List<String> encounterDate = status.getMissingParameters();
        assertEquals(encounterDate.contains("encounterDate"), true);
        System.out.print("Absent parameters: ");
        encounterDate.forEach(System.out::print);

    }

    @Disabled
    @Test
    public void checkForEmptyStatusMultipleFields() {
        status.setElementType(PewaType.MOVIE);
        status.setEncounterId(1);
        status.setEncounterRating(null);
        status.setComment("comment");
        status.setEncounterDate(null);
        status.setMediaSource(null);
        status.setSeason(2);
        status.setAddedDate(LocalDateTime.now());
        assertEquals(status.isEmpty(), true);
        assertEquals(status.getMissingParameters().contains("encounterRating"), true);
        assertEquals(status.getMissingParameters().contains("encounterDate"), true);
        assertEquals(status.getMissingParameters().contains("mediaSource"), true);
    }

    @Disabled
    @Test
    public void updateStatus() {
//        Status status = new Status();
        StatusRequest sreq = new StatusRequest();
        sreq.setMediaSource(MediaSource.COMPUTER);
        sreq.setEncounterDate(LocalDate.parse("2018-04-05"));
        sreq.setEncounterId(null);
        sreq.setStatusId(null);
        sreq.setEncounterRating(6);
        sreq.setComment("aktualizacja w module testów2");
        sreq.setElementType(PewaType.MOVIE);
        Status status = sreq.extractStatus();
        System.out.println(status);
        Results results = statusDAO.updateStatus(status);
        System.out.println(results);
    }


    @Disabled
    @Test
    public void getXStatuses() {
        Integer numberOfStatuses = 10;
        Results statusByNumber = statusDAO.getStatusByNumber(numberOfStatuses, results);

    }

    @Disabled
    @Test
    public void getLatestStatutes() {
//        Results statusByNumber = statusDAO.getStatusByNumber(30, new Results());
        Results statusByNumber = statusDAO.getStatusByNumber(30, new Results()).setReturnMessage();
        System.out.println(statusByNumber);
    }

}