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

    // TODO make more independent status dao method
    @PostMapping(value = "/add", consumes = json)
    public Results addStatus(@RequestBody StatusRequest request) {
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else {
            return statusDAO.addStatus(request.extractStatus());
        }
    }

    @PostMapping(value = "/update", consumes = json)
    public Results updateStatus(@RequestBody StatusRequest request) {
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else {
            return statusDAO.updateStatus(request.extractStatus());
        }
    }

    @GetMapping(value = "/delete/{statusId}")
    public Results deleteStatus(@PathVariable Integer statusId) {
        return statusDAO.deleteStatus(statusId);
    }

}
