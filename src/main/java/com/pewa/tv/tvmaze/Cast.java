
package com.pewa.tv.tvmaze;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "person",
    "character"
})
public class Cast {

    @JsonProperty("person")
    private Person person;
    @JsonProperty("character")
    private Character character;

    @JsonProperty("person")
    public Person getPerson() {
        return person;
    }

    @JsonProperty("person")
    public void setPerson(Person person) {
        this.person = person;
    }

    @JsonProperty("character")
    public Character getCharacter() {
        return character;
    }

    @JsonProperty("character")
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
        if ((other instanceof Cast) == false) {
            return false;
        }
        Cast rhs = ((Cast) other);
        return new EqualsBuilder().append(person, rhs.person).append(character, rhs.character).isEquals();
    }

}
