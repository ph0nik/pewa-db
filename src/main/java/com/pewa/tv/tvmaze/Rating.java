
package com.pewa.tv.tvmaze;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "average"
})
public class Rating {

    @JsonProperty("average")
    private double average;

    @JsonProperty("average")
    public double getAverage() {
        return average;
    }

    @JsonProperty("average")
    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("average", average).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(average).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rating) == false) {
            return false;
        }
        Rating rhs = ((Rating) other);
        return new EqualsBuilder().append(average, rhs.average).isEquals();
    }

}
