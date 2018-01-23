package com.pewa.common;

import com.pewa.PewaType;
import com.pewa.anime.Anime;
import com.pewa.status.Status;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by phonik on 2017-12-14.
 */
public class EncounterElement implements Comparable<EncounterElement>, Serializable, Encounter{
//    <!-- id, title, year, type, status -> id, encDate, addDate, rating -->
    private Integer id;
    private String title, engTitle;
    private Integer year;
    private PewaType type;
    private Set<Status> status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EncounterElement that = (EncounterElement) o;

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
        return "EncounterElement{" +
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
    public int compareTo(EncounterElement o) {
        return 0;
    }
}
