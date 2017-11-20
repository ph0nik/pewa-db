
package com.pewa.tv.tvmaze;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "self"
})
public class Links____ {

    @JsonProperty("self")
    private Self____ self;

    @JsonProperty("self")
    public Self____ getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self____ self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("self", self).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(self).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Links____) == false) {
            return false;
        }
        Links____ rhs = ((Links____) other);
        return new EqualsBuilder().append(self, rhs.self).isEquals();
    }

}
