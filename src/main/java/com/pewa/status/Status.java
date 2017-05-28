package com.pewa.common;

import com.pewa.MediaSource;
import com.pewa.PewaType;
import com.pewa.book.Book;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by phonik on 2017-05-10.
 */
@Component
public class Status {

    private PewaType elementType;
    private MediaSource mediaSource;
    private Encounter encounter;
    private LocalDate addedDate;
    private LocalDate encounterDate;
    private Integer encounterId;
    private Integer rating;
    private Boolean status;
    private String comment;

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }
}
