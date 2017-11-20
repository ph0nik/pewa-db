
package com.pewa.tv.tvmaze;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "url",
    "name",
    "season",
    "number",
    "airdate",
    "airtime",
    "airstamp",
    "runtime",
    "image",
    "summary",
    "_links"
})
public class Episode {

    @JsonProperty("id")
    private int id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("season")
    private int season;
    @JsonProperty("number")
    private int number;
    @JsonProperty("airdate")
    private String airdate;
    @JsonProperty("airtime")
    private String airtime;
    @JsonProperty("airstamp")
    private String airstamp;
    @JsonProperty("runtime")
    private int runtime;
    @JsonProperty("image")
    private Image___ image;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("_links")
    private Links____ links;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("season")
    public int getSeason() {
        return season;
    }

    @JsonProperty("season")
    public void setSeason(int season) {
        this.season = season;
    }

    @JsonProperty("number")
    public int getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(int number) {
        this.number = number;
    }

    @JsonProperty("airdate")
    public String getAirdate() {
        return airdate;
    }

    @JsonProperty("airdate")
    public void setAirdate(String airdate) {
        this.airdate = airdate;
    }

    @JsonProperty("airtime")
    public String getAirtime() {
        return airtime;
    }

    @JsonProperty("airtime")
    public void setAirtime(String airtime) {
        this.airtime = airtime;
    }

    @JsonProperty("airstamp")
    public String getAirstamp() {
        return airstamp;
    }

    @JsonProperty("airstamp")
    public void setAirstamp(String airstamp) {
        this.airstamp = airstamp;
    }

    @JsonProperty("runtime")
    public int getRuntime() {
        return runtime;
    }

    @JsonProperty("runtime")
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @JsonProperty("image")
    public Image___ getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Image___ image) {
        this.image = image;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("_links")
    public Links____ getLinks() {
        return links;
    }

    @JsonProperty("_links")
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
        if ((other instanceof Episode) == false) {
            return false;
        }
        Episode rhs = ((Episode) other);
        return new EqualsBuilder().append(summary, rhs.summary).append(airtime, rhs.airtime).append(airstamp, rhs.airstamp).append(image, rhs.image).append(runtime, rhs.runtime).append(links, rhs.links).append(number, rhs.number).append(url, rhs.url).append(id, rhs.id).append(season, rhs.season).append(name, rhs.name).append(airdate, rhs.airdate).isEquals();
    }

}
