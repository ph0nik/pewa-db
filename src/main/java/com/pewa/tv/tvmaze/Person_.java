
package com.pewa.tv.tvmaze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Person_ {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("_links")
    @Expose
    private Links___ links;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Links___ getLinks() {
        return links;
    }

    public void setLinks(Links___ links) {
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
        if (!(other instanceof Person_)) {
            return false;
        }
        Person_ rhs = ((Person_) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(image, rhs.image).append(links, rhs.links).append(url, rhs.url).isEquals();
    }

}
