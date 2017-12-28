package com.pewa.controller;

import com.pewa.InitAllTables;
import com.pewa.PewaType;
import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.request.StatusRequest;
import com.pewa.status.Status;
import com.pewa.status.StatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/status")
public class StatusController {

    private final String json = MediaType.APPLICATION_JSON_VALUE;
    private final String missingParameters = "Missing parameters: ";
    private final String emptyStatus = "Error: Empty status";

    @Autowired
    StatusDAO statusDAO;

    @Autowired
    private InitAllTables initAllTables;

    Results results;
    Status status;

    @GetMapping(value = "/{request}")
    public Results getLatestStatus(@PathVariable Integer request) {
        //TODO field this.getClass().getField()
        return statusDAO.getStatusByNumber(request, new Results()).setReturnMessage();
    }

    @PostMapping(value = "/date", consumes = json)
    public Results getStatusByDate(Request request) {
        return statusDAO.getStatusByDateRange(request, new Results()).setReturnMessage();
    }

    @PostMapping(value = "/add", consumes = json)
    public Results addStatus(@RequestBody StatusRequest request) {
        results = new Results();
        status = new Status();
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else if (!request.checkRequiredParameters().isEmpty()) {
            String returnMessage = missingParameters + request.checkRequiredParameters().toString();
            return results.setReturnMessage(returnMessage);
        } else {
            status.setElementType(request.getElementType());
            status.setEncounterId(request.getEncounterId());
            status.setComment(request.getComment());
            status.setEncounterRating(request.getEncounterRating());
            status.setEncounterDate(request.getEncounterDate());
            status.setMediaSource(request.getMediaSource());
            if (request.getElementType() == PewaType.TVSERIES) {
                status.setSeason(request.getSeason());
            }
            return statusDAO.addStatus(status, results);
        }
    }

    @PostMapping(value = "/update", consumes = json)
    public Results updateStatus(@RequestBody StatusRequest request) {
        results = new Results();
        status = new Status();
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else if (!request.checkRequiredParameters().isEmpty()) {
            String returnMessage = missingParameters + request.checkRequiredParameters().toString();
            return results.setReturnMessage(returnMessage);
        } else {
            status.setElementType(request.getElementType());
            status.setEncounterId(request.getEncounterId());
            status.setComment(request.getComment());
            status.setEncounterRating(request.getEncounterRating());
            status.setEncounterDate(request.getEncounterDate());
            status.setMediaSource(request.getMediaSource());
            status.setStatusId(request.getStatusId());
            if (request.getElementType() == PewaType.TVSERIES) {
                status.setSeason(request.getSeason());
            }
            return statusDAO.updateStatus(status, results);
        }
    }

    @GetMapping(value = "/delete/{statusId}")
    public Results deleteStatus(@PathVariable Integer statusId) {
        results = statusDAO.deleteStatus(statusId, new Results());
        return initAllTables.cleanAll(this.results);
    }

}
