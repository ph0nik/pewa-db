package com.pewa.anime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pewa.common.Genre;
import com.pewa.MediaModel;
import com.pewa.common.Person;
import com.pewa.util.CustomLocalDateDeserializer;
import com.pewa.util.CustomLocalDateSerializer;
import com.pewa.util.CustomLocalDateTimeDeserializer;
import com.pewa.util.CustomLocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class Anime extends MediaModel implements Serializable {
    private String titleRom, titleEng, type, description, poster, intPoster, airingStatus;

    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate startDate, endDate;
    private Set<Genre> genres;
    private Set<Person> staff;
    private Integer id, eps, duration, idAnilist;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime dbDatetime;

    static final long serialVersionUID = 1L;


    public Anime() {
        this.genres = new TreeSet<>();
        this.staff = new TreeSet<>();
    }

    public String getIntPoster() {
        return intPoster;
    }

    public void setIntPoster(String intPoster) {
        this.intPoster = intPoster;
    }

    public LocalDateTime getDbDatetime() {
        return dbDatetime;
    }

    public void setDbDatetime(LocalDateTime dbDatetime) {
        this.dbDatetime = dbDatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAnilist() {
        return idAnilist;
    }

    public void setIdAnilist(Integer idAnilist) {
        this.idAnilist = idAnilist;
    }

    public String getTitleRom() {
        return titleRom;
    }

    public void setTitleRom(String titleRom) {
        this.titleRom = titleRom;
    }

    public String getTitleEng() {
        return titleEng;
    }

    public void setTitleEng(String titleEnd) {
        this.titleEng = titleEnd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void setGenres(Genre genre) {
        this.genres.add(genre);
    }

    public Integer getEps() {
        return eps;
    }

    public void setEps(Integer eps) {
        this.eps = eps;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<Person> getStaff() {
        return staff;
    }

    public void setStaff(Set<Person> staff) {
        this.staff = staff;
    }

    public void setStaff(Person peron) {
        this.staff.add(peron);
    }

    public void setAiringStatus(String airingStatus) {
        this.airingStatus = airingStatus;
    }

    public String getAiringStatus() {
        return airingStatus;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "titleRom='" + titleRom + '\'' +
                ", titleEng='" + titleEng + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", airingStatus='" + airingStatus + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", genres=" + genres +
                ", staff=" + staff +
                ", id=" + id +
                ", eps=" + eps +
                ", duration=" + duration +
                ", idAnilist=" + idAnilist +
                ", dbDatetime=" + dbDatetime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Anime anime = (Anime) o;

        if (titleRom != null ? !titleRom.equals(anime.titleRom) : anime.titleRom != null) return false;
        if (titleEng != null ? !titleEng.equals(anime.titleEng) : anime.titleEng != null) return false;
        if (type != null ? !type.equals(anime.type) : anime.type != null) return false;
        if (description != null ? !description.equals(anime.description) : anime.description != null) return false;
        if (poster != null ? !poster.equals(anime.poster) : anime.poster != null) return false;
        if (intPoster != null ? !intPoster.equals(anime.intPoster) : anime.intPoster != null) return false;
        if (airingStatus != null ? !airingStatus.equals(anime.airingStatus) : anime.airingStatus != null) return false;
        if (startDate != null ? !startDate.equals(anime.startDate) : anime.startDate != null) return false;
        if (endDate != null ? !endDate.equals(anime.endDate) : anime.endDate != null) return false;
        if (genres != null ? !genres.equals(anime.genres) : anime.genres != null) return false;
        if (staff != null ? !staff.equals(anime.staff) : anime.staff != null) return false;
        if (id != null ? !id.equals(anime.id) : anime.id != null) return false;
        if (eps != null ? !eps.equals(anime.eps) : anime.eps != null) return false;
        if (duration != null ? !duration.equals(anime.duration) : anime.duration != null) return false;
        if (idAnilist != null ? !idAnilist.equals(anime.idAnilist) : anime.idAnilist != null) return false;
        return dbDatetime != null ? dbDatetime.equals(anime.dbDatetime) : anime.dbDatetime == null;
    }

    @Override
    public int hashCode() {
        int result = titleRom != null ? titleRom.hashCode() : 0;
        result = 31 * result + (titleEng != null ? titleEng.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (intPoster != null ? intPoster.hashCode() : 0);
        result = 31 * result + (airingStatus != null ? airingStatus.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (staff != null ? staff.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (eps != null ? eps.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (idAnilist != null ? idAnilist.hashCode() : 0);
        result = 31 * result + (dbDatetime != null ? dbDatetime.hashCode() : 0);
        return result;
    }

}
