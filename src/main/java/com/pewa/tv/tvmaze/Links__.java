
package com.pewa.tv.tvmaze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Links__ {

    @SerializedName("self")
    @Expose
    private Self__ self;

    public Self__ getSelf() {
        return self;
    }

    public void setSelf(Self__ self) {
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
        if (!(other instanceof Links__)) {
            return false;
        }
        Links__ rhs = ((Links__) other);
        return new EqualsBuilder().append(self, rhs.self).isEquals();
    }

}
