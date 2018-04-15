
package com.pewa.tv.tvmaze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Links {

    @SerializedName("self")
    @Expose
    private Self self;
    @SerializedName("previousepisode")
    @Expose
    private Previousepisode previousepisode;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Previousepisode getPreviousepisode() {
        return previousepisode;
    }

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
        if (!(other instanceof Links)) {
            return false;
        }
        Links rhs = ((Links) other);
        return new EqualsBuilder().append(previousepisode, rhs.previousepisode).append(self, rhs.self).isEquals();
    }

}
