
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
    "time",
    "days"
})
public class Schedule {

    @JsonProperty("time")
    private String time;
    @JsonProperty("days")
    private List<String> days = null;

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("days")
    public List<String> getDays() {
        return days;
    }

    @JsonProperty("days")
    public void setDays(List<String> days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("time", time).append("days", days).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(time).append(days).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Schedule) == false) {
            return false;
        }
        Schedule rhs = ((Schedule) other);
        return new EqualsBuilder().append(time, rhs.time).append(days, rhs.days).isEquals();
    }

}
