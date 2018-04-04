package com.pewa.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pewa.MediaModel;
import com.pewa.MediaSource;
import com.pewa.PewaType;
import com.pewa.util.CustomLocalDateDeserializer;
import com.pewa.util.CustomLocalDateSerializer;
import com.pewa.util.CustomLocalDateTimeDeserializer;
import com.pewa.util.CustomLocalDateTimeSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class Status extends MediaModel implements Comparable<Status>, Serializable {

    private PewaType elementType;
    private MediaSource mediaSource;
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime addedDate;
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate encounterDate;
    private Integer encounterId, statusId, encounterRating;
    private Integer season;
    private String comment;
    @JsonIgnore
    private List<String> missingParameters = new ArrayList<>();

    static final long serialVersionUID = 1L;

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
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

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
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

    public void setEncounterRating(Integer rating) {
        this.encounterRating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Status{" +
                "elementType=" + elementType +
                ", mediaSource=" + mediaSource +
                ", addedDate=" + addedDate +
                ", encounterDate=" + encounterDate +
                ", encounterId=" + encounterId +
                ", statusId=" + statusId +
                ", encounterRating=" + encounterRating +
                ", season=" + season +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        if (elementType != status.elementType) return false;
        if (mediaSource != status.mediaSource) return false;
        if (addedDate != null ? !addedDate.equals(status.addedDate) : status.addedDate != null) return false;
        if (encounterDate != null ? !encounterDate.equals(status.encounterDate) : status.encounterDate != null)
            return false;
        if (encounterId != null ? !encounterId.equals(status.encounterId) : status.encounterId != null) return false;
        if (statusId != null ? !statusId.equals(status.statusId) : status.statusId != null) return false;
        if (encounterRating != null ? !encounterRating.equals(status.encounterRating) : status.encounterRating != null)
            return false;
        return comment != null ? comment.equals(status.comment) : status.comment == null;
    }

    @Override
    public int hashCode() {
        int result = elementType != null ? elementType.hashCode() : 0;
        result = 31 * result + (mediaSource != null ? mediaSource.hashCode() : 0);
        result = 31 * result + (addedDate != null ? addedDate.hashCode() : 0);
        result = 31 * result + (encounterDate != null ? encounterDate.hashCode() : 0);
        result = 31 * result + (encounterId != null ? encounterId.hashCode() : 0);
        result = 31 * result + (statusId != null ? statusId.hashCode() : 0);
        result = 31 * result + (encounterRating != null ? encounterRating.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    public void checkRequiredParameters() {
//        List<String> missingParameters = new ArrayList<>();
        try {
            if (this.elementType.equals(PewaType.TVSERIES)) {
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
//        return missingParameters;
    }

    public List<String> getMissingParameters() {
        return missingParameters;
    }

    private static Comparator<String> nullSafeStringComparator = Comparator.nullsFirst(String::compareTo);
    private static Comparator<Integer> nullSafeIntegerComparator = Comparator.nullsFirst(Integer::compareTo);
    private static Comparator<LocalDate> nullSafeLocalDateComparator = Comparator.nullsFirst(LocalDate::compareTo);
    private static Comparator<LocalDateTime> nullSafeLocalDateTimeComparator = Comparator.nullsFirst(LocalDateTime::compareTo);

    private static Comparator<Status> statusComparator = Comparator.comparing(Status::getEncounterDate, nullSafeLocalDateComparator)
            .thenComparing(Status::getStatusId, nullSafeIntegerComparator)
            .thenComparing(Status::getEncounterRating, nullSafeIntegerComparator)
            .thenComparing(Status::getAddedDate, nullSafeLocalDateTimeComparator);

    @Override
    public int compareTo(Status status) {
        return statusComparator.compare(this, status);
    }

    @Override
    public boolean isEmpty() {
        checkRequiredParameters();
        if (missingParameters.isEmpty()) return false;
        else return true;
    }
}
