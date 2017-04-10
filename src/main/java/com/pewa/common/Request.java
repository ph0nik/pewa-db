package com.pewa.common;

import com.pewa.PewaType;

/**
 * Created by phonik on 2017-04-09.
 */
public class Request {
    private PewaType type;
    private Integer id;
    private Integer dateIn;
    private Integer dateOut;
    private String dateInString;
    private String dateOutString;
    private String imdbId;
    private Integer aniId;

    public Integer getAniId() {
        return aniId;
    }

    public void setAniId(Integer aniId) {
        this.aniId = aniId;
    }

    public String getDateInString() {
        return dateInString;
    }

    public void setDateInString(String dateInString) {
        this.dateInString = dateInString;
    }

    public String getDateOutString() {
        return dateOutString;
    }

    public void setDateOutString(String dateOutString) {
        this.dateOutString = dateOutString;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public PewaType getType() {
        return type;
    }

    public void setType(PewaType type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDateIn() {
        return dateIn;
    }

    public void setDateIn(Integer dateIn) {
        this.dateIn = dateIn;
    }

    public Integer getDateOut() {
        return dateOut;
    }

    public void setDateOut(Integer dateOut) {
        this.dateOut = dateOut;
    }
}
