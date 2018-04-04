package com.pewa.status;



import com.pewa.MediaSource;
import com.pewa.PewaType;
import com.pewa.common.Request;
import com.pewa.common.Results;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;

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
        assertEquals(status.isEmpty(), true);
        assertEquals(status.getMissingParameters().contains("encounterDate"), true);
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
        Status status = new Status();
        status.setMediaSource(MediaSource.CINEMA);
        status.setEncounterDate(LocalDate.parse("2001-11-16"));
        status.setEncounterId(218);
        status.setStatusId(1);
        status.setEncounterRating(8);
        status.setComment("termit update");
        status.setElementType(PewaType.MOVIE);
        Results results = statusDAO.updateStatus(status);
        System.out.println(results);
    }

    @Disabled
    @Test
    public void nullStatus() {
        Request request = new Request();
        Status status = new Status();
//        System.out.println(status.checkRequiredParameters());
        PewaType type = PewaType.TVSERIES;
        request.setEncounterType(PewaType.TVSERIES);
        assertEquals(PewaType.TVSERIES, request.getEncounterType());
        request.setStatus(status);
        assertNotNull(request.getStatus().getElementType());
        assertEquals(PewaType.TVSERIES, request.getStatus().getElementType());


        Request request1 = new Request();
        assertNull(request1.getStatus());
        Status status1 = new Status();
        request1.setStatus(status1);
        assertNull(request1.getStatus().getElementType());
        request1.setEncounterType(PewaType.ANIME);
        assertNotNull(request1.getStatus().getElementType());

//        System.out.println(request1.getStatus().checkRequiredParameters(PewaType.ANIME));
        request1.getStatus().setEncounterRating(4);
//        System.out.println(request1.getStatus().checkRequiredParameters(PewaType.ANIME));
    }

    @Disabled
    @Test
    public void getXStatuses() {
        Integer numberOfStatuses = 10;
        Results statusByNumber = statusDAO.getStatusByNumber(numberOfStatuses, results);

    }

}