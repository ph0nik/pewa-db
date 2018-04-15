
package com.pewa.tv.tvmaze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Cast {

    @SerializedName("person")
    @Expose
    private Person person;
    @SerializedName("character")
    @Expose
    private Character character;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("person", person).append("character", character).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(person).append(character).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Cast)) {
            return false;
        }
        Cast rhs = ((Cast) other);
        return new EqualsBuilder().append(person, rhs.person).append(character, rhs.character).isEquals();
    }

}
