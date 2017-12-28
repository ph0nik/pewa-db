
package com.pewa.tv.tvmaze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Externals {

    @SerializedName("tvrage")
    @Expose
    private int tvrage;
    @SerializedName("thetvdb")
    @Expose
    private int thetvdb;
    @SerializedName("imdb")
    @Expose
    private String imdb;

    public int getTvrage() {
        return tvrage;
    }

    public void setTvrage(int tvrage) {
        this.tvrage = tvrage;
    }

    public int getThetvdb() {
        return thetvdb;
    }

    public void setThetvdb(int thetvdb) {
        this.thetvdb = thetvdb;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("tvrage", tvrage).append("thetvdb", thetvdb).append("imdb", imdb).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(thetvdb).append(imdb).append(tvrage).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Externals) == false) {
            return false;
        }
        Externals rhs = ((Externals) other);
        return new EqualsBuilder().append(thetvdb, rhs.thetvdb).append(imdb, rhs.imdb).append(tvrage, rhs.tvrage).isEquals();
    }

}
