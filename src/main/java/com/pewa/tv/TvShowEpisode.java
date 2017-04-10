package com.pewa.tv;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class TvShowEpisode implements Serializable, Comparable<TvShowEpisode> {
    private String tvMazeUrl, epTitle, summary;
    private Integer id, tvMazeId, season, episode;
    private LocalDate firstAired;
    private LocalDateTime dbDatetime;

    static final long serialVersionUID = 1L;

    public TvShowEpisode() {}

    public String getTvMazeUrl() {
        return tvMazeUrl;
    }

    public void setTvMazeUrl(String tvMazeUrl) {
        this.tvMazeUrl = tvMazeUrl;
    }

    public String getEpTitle() {
        return epTitle;
    }

    public void setEpTitle(String epTitle) {
        this.epTitle = epTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTvMazeId() {
        return tvMazeId;
    }

    public void setTvMazeId(Integer tvMazeId) {
        this.tvMazeId = tvMazeId;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public LocalDate getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(LocalDate firstAired) {
        this.firstAired = firstAired;
    }

    public LocalDateTime getDbDatetime() {
        return dbDatetime;
    }

    public void setDbDatetime(LocalDateTime dbDatetime) {
        this.dbDatetime = dbDatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TvShowEpisode that = (TvShowEpisode) o;

        if (tvMazeUrl != null ? !tvMazeUrl.equals(that.tvMazeUrl) : that.tvMazeUrl != null) return false;
        if (epTitle != null ? !epTitle.equals(that.epTitle) : that.epTitle != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (tvMazeId != null ? !tvMazeId.equals(that.tvMazeId) : that.tvMazeId != null) return false;
        if (season != null ? !season.equals(that.season) : that.season != null) return false;
        if (episode != null ? !episode.equals(that.episode) : that.episode != null) return false;
        return firstAired != null ? firstAired.equals(that.firstAired) : that.firstAired == null;
    }

    @Override
    public int hashCode() {
        int result = tvMazeUrl != null ? tvMazeUrl.hashCode() : 0;
        result = 31 * result + (epTitle != null ? epTitle.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (tvMazeId != null ? tvMazeId.hashCode() : 0);
        result = 31 * result + (season != null ? season.hashCode() : 0);
        result = 31 * result + (episode != null ? episode.hashCode() : 0);
        result = 31 * result + (firstAired != null ? firstAired.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TvShowEpisode{" +
                "tvMazeUrl='" + tvMazeUrl + '\'' +
                ", epTitle='" + epTitle + '\'' +
                ", summary='" + summary + '\'' +
                ", id=" + id +
                ", tvMazeId=" + tvMazeId +
                ", season=" + season +
                ", episode=" + episode +
                ", firstAired=" + firstAired +
                ", dbDatetime=" + dbDatetime +
                '}';
    }

    @Override
    public int compareTo(TvShowEpisode ep) {
        if (this.season.equals(ep.season)) {
            return this.episode.compareTo(ep.episode);
        } else {
            return this.season.compareTo(ep.season);
        }
    }
}
