
package com.pewa.tv.tvmaze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Episode {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("season")
    @Expose
    private int season;
    @SerializedName("number")
    @Expose
    private int number;
    @SerializedName("airdate")
    @Expose
    private String airdate;
    @SerializedName("airtime")
    @Expose
    private String airtime;
    @SerializedName("airstamp")
    @Expose
    private String airstamp;
    @SerializedName("runtime")
    @Expose
    private int runtime;
    @SerializedName("image")
    @Expose
    private Image___ image;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("_links")
    @Expose
    private Links____ links;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAirdate() {
        return airdate;
    }

    public void setAirdate(String airdate) {
        this.airdate = airdate;
    }

    public String getAirtime() {
        return airtime;
    }

    public void setAirtime(String airtime) {
        this.airtime = airtime;
    }

    public String getAirstamp() {
        return airstamp;
    }

    public void setAirstamp(String airstamp) {
        this.airstamp = airstamp;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Image___ getImage() {
        return image;
    }

    public void setImage(Image___ image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Links____ getLinks() {
        return links;
    }

    public void setLinks(Links____ links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("url", url).append("name", name).append("season", season).append("number", number).append("airdate", airdate).append("airtime", airtime).append("airstamp", airstamp).append("runtime", runtime).append("image", image).append("summary", summary).append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(summary).append(airtime).append(airstamp).append(image).append(runtime).append(links).append(number).append(url).append(id).append(season).append(name).append(airdate).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Episode)) {
            return false;
        }
        Episode rhs = ((Episode) other);
        return new EqualsBuilder().append(summary, rhs.summary).append(airtime, rhs.airtime).append(airstamp, rhs.airstamp).append(image, rhs.image).append(runtime, rhs.runtime).append(links, rhs.links).append(number, rhs.number).append(url, rhs.url).append(id, rhs.id).append(season, rhs.season).append(name, rhs.name).append(airdate, rhs.airdate).isEquals();
    }

}
