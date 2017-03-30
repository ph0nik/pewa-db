package com.pewa.anime;

import com.pewa.Genre;
import com.pewa.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Manga {
    private Set<Genre> genres;
    private Set<Person> staff;
    private String titleRom, titleEng, type, description, poster, publishingStatus, intPoster;
    private LocalDate startDate, endDate;
    private Integer id, totalChapters, totalVolumes, idAnilist;
    private LocalDateTime dbDatetime;

    public String getIntPoster() {
        return intPoster;
    }

    public void setIntPoster(String intPoster) {
        this.intPoster = intPoster;
    }

    public Integer getIdAnilist() {
        return idAnilist;
    }

    public void setIdAnilist(Integer idAnilist) {
        this.idAnilist = idAnilist;
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

    public String getTitleRom() {
        return titleRom;
    }

    public void setTitleRom(String titleRom) {
        this.titleRom = titleRom;
    }

    public String getTitleEng() {
        return titleEng;
    }

    public void setTitleEng(String titleEng) {
        this.titleEng = titleEng;
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

    public String getPublishingStatus() {
        return publishingStatus;
    }

    public void setPublishingStatus(String publishingStatus) {
        this.publishingStatus = publishingStatus;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Genre genre) {
        if(this.genres==null) {
            this.genres = new TreeSet<>();
        }
        this.genres.add(genre);
    }
    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Integer getTotalChapters() {
        return totalChapters;
    }

    public void setTotalChapters(Integer totalChapters) {
        this.totalChapters = totalChapters;
    }

    public Integer getTotalVolumes() {
        return totalVolumes;
    }

    public void setTotalVolumes(Integer totalVolumes) {
        this.totalVolumes = totalVolumes;
    }

    public Set<Person> getStaff() {
        return staff;
    }

    public void setStaff(Person person) {
        if(this.staff==null) {
            this.staff = new TreeSet<>();
        }
        this.staff.add(person);
    }

    public void setStaff(Set<Person> staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Manga{" +
                "genres=" + genres +
                ", staff=" + staff +
                ", titleRom='" + titleRom + '\'' +
                ", titleEng='" + titleEng + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", publishingStatus='" + publishingStatus + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", id=" + id +
                ", totalChapters=" + totalChapters +
                ", totalVolumes=" + totalVolumes +
                ", idAnilist=" + idAnilist +
                ", dbDatetime=" + dbDatetime +
                '}';
    }
}
