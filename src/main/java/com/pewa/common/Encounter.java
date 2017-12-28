package com.pewa.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pewa.PewaType;
import com.pewa.status.Status;

import java.util.Set;

/**
 * Created by phonik on 2017-05-27.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Encounter {

    PewaType getType();

    void setType(PewaType type);
}
