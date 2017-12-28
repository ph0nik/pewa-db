package com.pewa.anime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pewa.PewaType;
import com.pewa.common.Encounter;
import com.pewa.common.Genre;
import com.pewa.common.Person;
import com.pewa.status.Status;
import com.pewa.util.CustomLocalDateDeserializer;
import com.pewa.util.CustomLocalDateSerializer;
import com.pewa.util.CustomLocalDateTimeDeserializer;
import com.pewa.util.CustomLocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class Manga implements Comparable<Manga>, Serializable, Encounter {
    private Set<Genre> genres;
    private Set<Person> staff;
    private String titleRom, titleEng, description, poster, publishingStatus, intPoster, mangaType;
    private PewaType type;
    private Set<Status> status;

    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate startDate, endDate;
    private Integer id, totalChapters, totalVolumes, idAnilist;
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime dbDatetime;

    static final long serialVersionUID = 1L;

    public Manga() {
        this.genres = new TreeSet<>();
        this.staff = new TreeSet<>();
        this.status = new TreeSet<>();
    }

    public String getMangaType() {
        return mangaType;
    }

    public void setMangaType(String mangaType) {
        this.mangaType = mangaType;
    }

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

    public void setStatus(Status status) {
        this.status.add(status);
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

    @Override
    public int compareTo(Manga manga) {
        return this.titleRom.compareTo(manga.titleRom);
    }
}
