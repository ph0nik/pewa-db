package com.pewa.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pewa.MediaSource;
import com.pewa.PewaType;
import com.pewa.status.Status;
import org.springframework.stereotype.Component;

/**
 * Created by phonik on 2017-04-09.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class Request {
    private Integer externalId, year;
    private String dateInString, dateOutString;
    private Status status;
    private PewaType encounterType;


    public Integer getYear() {
        return year;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        if (this.encounterType != null) this.status.setElementType(this.getEncounterType());
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public PewaType getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(PewaType encounterType) {
        this.encounterType = encounterType;
        if (this.status != null) this.status.setElementType(this.getEncounterType());
    }


    public String getDateInString() {
        return dateInString;
    }

    public void setDateInString(String dateInString) {
        this.dateInString = dateInString;
    }

    public String getDateOutString() {
        return dateOutString;
    }

    public void setDateOutString(String dateOutString) {
        this.dateOutString = dateOutString;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    private MediaSource assignMediaSource(String mediaSource) {
        if (mediaSource.toLowerCase().equals("cinema")) return MediaSource.CINEMA;
        else if (mediaSource.toLowerCase().equals("computer")) return MediaSource.COMPUTER;
        else if (mediaSource.toLowerCase().equals("webstream")) return MediaSource.WEBSTREAM;
        else if (mediaSource.toLowerCase().equals("tv")) return MediaSource.TV;
        else if (mediaSource.toLowerCase().equals("video")) return MediaSource.VIDEO;
        else if (mediaSource.toLowerCase().equals("dvd")) return MediaSource.DVD;
        else if (mediaSource.toLowerCase().equals("bluray")) return MediaSource.BLURAY;
        else if (mediaSource.toLowerCase().equals("book")) return MediaSource.BOOK;
        else if (mediaSource.toLowerCase().equals("ebook")) return MediaSource.EBOOK;
        else return MediaSource.OTHER;
    }

    @Override
    public String toString() {
        return "Request{" +
                "externalId=" + externalId +
                ", year=" + year +
                ", dateInString='" + dateInString + '\'' +
                ", dateOutString='" + dateOutString + '\'' +
                ", status=" + status +
                ", encounterType=" + encounterType +
                '}';
    }
}
