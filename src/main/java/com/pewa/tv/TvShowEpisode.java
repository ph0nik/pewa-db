package com.pewa.tv;

import java.util.Date;

public class TvShowEpisode {
    private int tvMazeId;
    private String tvMazeUrl;
    private String epTitle;
    private int season;
    private int episode;
    private Date firstAired;
    private String summary;

    TvShowEpisode(int tvMazeId, String tvMazeUrl, String epTitle, int season, int episode, Date firstAired, String summary) {
        this.tvMazeId = tvMazeId;
        this.tvMazeUrl = tvMazeUrl;
        this.epTitle = epTitle;
        this.season = season;
        this.episode = episode;
        this.firstAired = firstAired;
        this.summary = summary;
    }

    public int getTvMazeId() {
        return tvMazeId;
    }

    public String getTvMazeUrl() {
        return tvMazeUrl;
    }

    public String getEpTitle() {
        return epTitle;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }

    public Date getFirstAired() {
        return firstAired;
    }

    public String getSummary() {
        return summary;
    }

    @Override
    public String toString() {
        return "com.pewa.tv.TvShowEpisode{" +
                "tvMazeId=" + tvMazeId +
                ", tvMazeUrl='" + tvMazeUrl + '\'' +
                ", epTitle='" + epTitle + '\'' +
                ", season=" + season +
                ", episode=" + episode +
                ", firstAired=" + firstAired +
                ", summary='" + summary + '\'' +
                '}';
    }
}
