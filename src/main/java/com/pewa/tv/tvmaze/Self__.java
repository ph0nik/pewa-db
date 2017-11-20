
package com.pewa.tv.tvmaze;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "href"
})
public class Self__ {

    @JsonProperty("href")
    private String href;

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("href", href).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(href).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Self__) == false) {
            return false;
        }
        Self__ rhs = ((Self__) other);
        return new EqualsBuilder().append(href, rhs.href).isEquals();
    }

}
