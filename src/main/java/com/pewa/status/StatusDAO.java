package com.pewa.status;

import com.pewa.PewaType;
import com.pewa.common.Request;
import com.pewa.common.Results;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

public interface StatusDAO {
    // TODO zrobić implementację, mapper i kontroler

    Status setProperStatus(Request request, PewaType pewaType);

    Results addStatus(Status status, Results results);

    Results updateStatus(Status status, Results results);

    Results deleteStatus(Integer statusId, Results results);

    Results getStatusByNumber(Integer numberOfStatuses, Results results);

    Results getStatusByDateRange(Request request, Results results);


}
