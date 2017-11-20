
package com.pewa.tv.tvmaze;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cast",
    "crew",
    "episodes"
})
public class Embedded {

    @JsonProperty("cast")
    private List<Cast> cast = null;
    @JsonProperty("crew")
    private List<Crew> crew = null;
    @JsonProperty("episodes")
    private List<Episode> episodes = null;

    @JsonProperty("cast")
    public List<Cast> getCast() {
        return cast;
    }

    @JsonProperty("cast")
    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    @JsonProperty("crew")
    public List<Crew> getCrew() {
        return crew;
    }

    @JsonProperty("crew")
    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    @JsonProperty("episodes")
    public List<Episode> getEpisodes() {
        return episodes;
    }

    @JsonProperty("episodes")
    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("cast", cast).append("crew", crew).append("episodes", episodes).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cast).append(crew).append(episodes).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Embedded) == false) {
            return false;
        }
        Embedded rhs = ((Embedded) other);
        return new EqualsBuilder().append(cast, rhs.cast).append(crew, rhs.crew).append(episodes, rhs.episodes).isEquals();
    }

}
