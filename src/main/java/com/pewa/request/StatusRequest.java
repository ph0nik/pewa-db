package com.pewa.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pewa.MediaSource;
import com.pewa.PewaType;
import com.pewa.util.CustomLocalDateDeserializer;
import com.pewa.util.CustomLocalDateSerializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StatusRequest {

    private PewaType elementType;
    private MediaSource mediaSource;
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate encounterDate;
    private Integer encounterId, encounterRating;
    private Integer season;
    private String comment;
    private int statusId;

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public PewaType getElementType() {
        return elementType;
    }

    public void setElementType(PewaType elementType) {
        this.elementType = elementType;
    }

    public MediaSource getMediaSource() {
        return mediaSource;
    }

    public void setMediaSource(MediaSource mediaSource) {
        this.mediaSource = mediaSource;
    }

    public LocalDate getEncounterDate() {
        return encounterDate;
    }

    public void setEncounterDate(LocalDate encounterDate) {
        this.encounterDate = encounterDate;
    }

    public Integer getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Integer encounterId) {
        this.encounterId = encounterId;
    }

    public Integer getEncounterRating() {
        return encounterRating;
    }

    public void setEncounterRating(Integer encounterRating) {
        this.encounterRating = encounterRating;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> checkRequiredParameters() {
        List<String> missingParameters = new ArrayList<>();
        try {
            if (this.getElementType() != null && this.getElementType().equals(PewaType.TVSERIES)) {
                if (this.season == null)
                    missingParameters.add(this.getClass().getDeclaredField("season").getName());
            } else {
                if (this.mediaSource == null)
                    missingParameters.add(this.getClass().getDeclaredField("mediaSource").getName());
                if (this.encounterDate == null)
                    missingParameters.add(this.getClass().getDeclaredField("encounterDate").getName());
                if (this.encounterRating == null)
                    missingParameters.add(this.getClass().getDeclaredField("encounterRating").getName());
                if (this.comment == null)
                    missingParameters.add(this.getClass().getDeclaredField("comment").getName());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return missingParameters;
    }
}
