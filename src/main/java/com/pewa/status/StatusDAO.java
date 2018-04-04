package com.pewa.status;

import com.pewa.PewaType;
import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.request.StatusRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

public interface StatusDAO {
    // TODO zrobić implementację, mapper i kontroler

    Status setProperStatus(Request request, PewaType pewaType);

    // adds status info to database
    Results addStatus(Status status);

    // updates existing status
    Results updateStatus(Status status);

    // deletes status based on given id
    Results deleteStatus(Integer statusId);

    // return list of given length that contains latest statuses
    Results getStatusByNumber(Integer numberOfStatuses, Results results);

    // returns list of statuses based on dates range
    Results getStatusByDateRange(Request request, Results results);


}
