
package com.pewa.tv.tvmaze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Links____ {

    @SerializedName("self")
    @Expose
    private Self____ self;

    public Self____ getSelf() {
        return self;
    }

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
        if (!(other instanceof Links____)) {
            return false;
        }
        Links____ rhs = ((Links____) other);
        return new EqualsBuilder().append(self, rhs.self).isEquals();
    }

}
