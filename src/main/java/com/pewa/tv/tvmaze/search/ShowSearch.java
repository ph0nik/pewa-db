
package com.pewa.tv.tvmaze.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowSearch {

    @SerializedName("score")
    @Expose
    private double score;
    @SerializedName("show")
    @Expose
    private Show show;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

}
