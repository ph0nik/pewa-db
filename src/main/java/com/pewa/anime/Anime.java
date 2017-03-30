package com.pewa.anime;

import com.pewa.Genre;
import com.pewa.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class Anime {
    private String titleRom, titleEng, type, description, poster, intPoster, airingStatus;
    private LocalDate startDate, endDate;
    private Set<Genre> genres;
    private Set<Person> staff;
    private Integer id, eps, duration, idAnilist;
    private LocalDateTime dbDatetime;
    //TODO dodac mape osob wg elementu Person

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
}
