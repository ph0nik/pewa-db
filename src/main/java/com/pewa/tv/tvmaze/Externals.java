
package com.pewa.tv.tvmaze;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tvrage",
    "thetvdb",
    "imdb"
})
public class Externals {

    @JsonProperty("tvrage")
    private int tvrage;
    @JsonProperty("thetvdb")
    private int thetvdb;
    @JsonProperty("imdb")
    private String imdb;

    @JsonProperty("tvrage")
    public int getTvrage() {
        return tvrage;
    }

    @JsonProperty("tvrage")
    public void setTvrage(int tvrage) {
        this.tvrage = tvrage;
    }

    @JsonProperty("thetvdb")
    public int getThetvdb() {
        return thetvdb;
    }

    @JsonProperty("thetvdb")
    public void setThetvdb(int thetvdb) {
        this.thetvdb = thetvdb;
    }

    @JsonProperty("imdb")
    public String getImdb() {
        return imdb;
    }

    @JsonProperty("imdb")
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
