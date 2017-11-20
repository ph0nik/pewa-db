
package com.pewa.tv.tvmaze;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "person"
})
public class Crew {

    @JsonProperty("type")
    private String type;
    @JsonProperty("person")
    private Person_ person;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("person")
    public Person_ getPerson() {
        return person;
    }

    @JsonProperty("person")
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
