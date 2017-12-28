package com.pewa.movie.tmdb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Title {

    @SerializedName("iso_3166_1")
    @Expose
    private String iso31661;
    @SerializedName("title")
    @Expose
    private String title;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("iso31661", iso31661).append("title", title).toString();
    }

}