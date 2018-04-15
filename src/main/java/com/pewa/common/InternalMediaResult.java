package com.pewa.common;

import com.pewa.MediaModel;
import com.pewa.PewaType;
import com.pewa.status.Status;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

//TODO work on abstract super class for ExternalMediaResult and InternalMediaResult objects
/**
 * Created by phonik on 2017-12-14.
 */
public class InternalMediaResult extends MediaModel implements Comparable<InternalMediaResult>, Serializable {
    // Internal db id
    private Integer id;
    // original title and english title
    private String title, engTitle;
    // year that production ended
    private Integer year;
    // internal object type
    private PewaType type;
    // collection of status objects
    private Set<Status> status;

    public InternalMediaResult() {
        this.status = new TreeSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InternalMediaResult that = (InternalMediaResult) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (engTitle != null ? !engTitle.equals(that.engTitle) : that.engTitle != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (type != that.type) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (engTitle != null ? engTitle.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InternalMediaResult{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", titleEng='" + engTitle + '\'' +
                ", year=" + year +
                ", type=" + type +
                ", status=" + status +
                '}';
    }

    public String getEngTitle() {
        return engTitle;
    }

    public void setEngTitle(String titleEng) {
        this.engTitle = titleEng;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    // dodać serializer w razie problemów
    public void setYear(LocalDate date) {
        this.year = date.getYear();
    }

    public PewaType getType() {
        return type;
    }

    public void setType(PewaType type) {
        this.type = type;
    }

    public Set<Status> getStatus() {
        return status;
    }

    public void setStatus(Set<Status> status) {
        this.status = status;
    }

    @Override
    public int compareTo(InternalMediaResult o) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
