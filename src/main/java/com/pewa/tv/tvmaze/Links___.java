
package com.pewa.tv.tvmaze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Links___ {

    @SerializedName("self")
    @Expose
    private Self___ self;

    public Self___ getSelf() {
        return self;
    }

    public void setSelf(Self___ self) {
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
        if (!(other instanceof Links___)) {
            return false;
        }
        Links___ rhs = ((Links___) other);
        return new EqualsBuilder().append(self, rhs.self).isEquals();
    }

}
