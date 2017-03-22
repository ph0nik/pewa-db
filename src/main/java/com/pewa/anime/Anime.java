package com.pewa.anime;

import com.pewa.Genre;
import com.pewa.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class Anime {
    private String titleRom;
    private String titleEng;
    private String type;
    private String description;
    private String poster;
    private String airingStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<Genre> genres;
    private Set<Person> staff;
    private int id;
    private int eps;
    private int duration;
    private int idAnilist;
    private LocalDateTime dbDatetime;
    //TODO dodac mape osob wg elementu Person


    public LocalDateTime getDbDatetime() {
        return dbDatetime;
    }

    public void setDbDatetime(LocalDateTime dbDatetime) {
        this.dbDatetime = dbDatetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnilist() {
        return idAnilist;
    }

    public void setIdAnilist(int idAnilist) {
        this.idAnilist = idAnilist;
    }

    public Anime() {
        this.genres = new TreeSet<>();
        this.staff = new TreeSet<>();
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
    public int getEps() {
        return eps;
    }

    public void setEps(int eps) {
        this.eps = eps;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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
