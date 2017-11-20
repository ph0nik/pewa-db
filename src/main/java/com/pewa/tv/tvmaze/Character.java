
package com.pewa.tv.tvmaze;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "url",
    "name",
    "image",
    "_links"
})
public class Character {

    @JsonProperty("id")
    private int id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("image")
    private Image__ image;
    @JsonProperty("_links")
    private Links__ links;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("image")
    public Image__ getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Image__ image) {
        this.image = image;
    }

    @JsonProperty("_links")
    public Links__ getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(Links__ links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("url", url).append("name", name).append("image", image).append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(image).append(links).append(url).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Character) == false) {
            return false;
        }
        Character rhs = ((Character) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(image, rhs.image).append(links, rhs.links).append(url, rhs.url).isEquals();
    }

}
