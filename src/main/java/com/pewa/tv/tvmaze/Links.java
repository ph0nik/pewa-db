
package com.pewa.tv.tvmaze;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "self",
    "previousepisode"
})
public class Links {

    @JsonProperty("self")
    private Self self;
    @JsonProperty("previousepisode")
    private Previousepisode previousepisode;

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("previousepisode")
    public Previousepisode getPreviousepisode() {
        return previousepisode;
    }

    @JsonProperty("previousepisode")
    public void setPreviousepisode(Previousepisode previousepisode) {
        this.previousepisode = previousepisode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("self", self).append("previousepisode", previousepisode).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(previousepisode).append(self).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Links) == false) {
            return false;
        }
        Links rhs = ((Links) other);
        return new EqualsBuilder().append(previousepisode, rhs.previousepisode).append(self, rhs.self).isEquals();
    }

}
