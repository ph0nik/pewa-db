
package com.pewa.tv.tvmaze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Crew {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("person")
    @Expose
    private Person_ person;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Person_ getPerson() {
        return person;
    }

    public void setPerson(Person_ person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("type", type).append("person", person).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(person).append(type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Crew) == false) {
            return false;
        }
        Crew rhs = ((Crew) other);
        return new EqualsBuilder().append(person, rhs.person).append(type, rhs.type).isEquals();
    }

}
