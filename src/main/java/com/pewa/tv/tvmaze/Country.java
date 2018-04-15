
package com.pewa.tv.tvmaze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Country {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("timezone")
    @Expose
    private String timezone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("code", code).append("timezone", timezone).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(timezone).append(name).append(code).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Country)) {
            return false;
        }
        Country rhs = ((Country) other);
        return new EqualsBuilder().append(timezone, rhs.timezone).append(name, rhs.name).append(code, rhs.code).isEquals();
    }

}
