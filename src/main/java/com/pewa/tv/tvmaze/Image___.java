
package com.pewa.tv.tvmaze;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "medium",
    "original"
})
public class Image___ {

    @JsonProperty("medium")
    private String medium;
    @JsonProperty("original")
    private String original;

    @JsonProperty("medium")
    public String getMedium() {
        return medium;
    }

    @JsonProperty("medium")
    public void setMedium(String medium) {
        this.medium = medium;
    }

    @JsonProperty("original")
    public String getOriginal() {
        return original;
    }

    @JsonProperty("original")
    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("medium", medium).append("original", original).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(original).append(medium).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Image___) == false) {
            return false;
        }
        Image___ rhs = ((Image___) other);
        return new EqualsBuilder().append(original, rhs.original).append(medium, rhs.medium).isEquals();
    }

}
