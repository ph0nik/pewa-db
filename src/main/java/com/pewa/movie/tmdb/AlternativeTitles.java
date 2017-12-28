package com.pewa.movie.tmdb;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AlternativeTitles {

    @SerializedName("titles")
    @Expose
    private List<Title> titles = null;

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("titles", titles).toString();
    }

}