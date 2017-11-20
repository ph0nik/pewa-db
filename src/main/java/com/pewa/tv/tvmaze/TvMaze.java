
package com.pewa.tv.tvmaze;

import java.util.List;
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
    "type",
    "language",
    "genres",
    "status",
    "runtime",
    "premiered",
    "officialSite",
    "schedule",
    "rating",
    "weight",
    "network",
    "webChannel",
    "externals",
    "image",
    "summary",
    "updated",
    "_links",
    "_embedded"
})
public class TvMaze {

    @JsonProperty("id")
    private int id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("language")
    private String language;
    @JsonProperty("genres")
    private List<String> genres = null;
    @JsonProperty("status")
    private String status;
    @JsonProperty("runtime")
    private int runtime;
    @JsonProperty("premiered")
    private String premiered;
    @JsonProperty("officialSite")
    private Object officialSite;
    @JsonProperty("schedule")
    private Schedule schedule;
    @JsonProperty("rating")
    private Rating rating;
    @JsonProperty("weight")
    private int weight;
    @JsonProperty("network")
    private Network network;
    @JsonProperty("webChannel")
    private Object webChannel;
    @JsonProperty("externals")
    private Externals externals;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("updated")
    private int updated;
    @JsonProperty("_links")
    private Links links;
    @JsonProperty("_embedded")
    private Embedded embedded;

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

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("genres")
    public List<String> getGenres() {
        return genres;
    }

    @JsonProperty("genres")
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("runtime")
    public int getRuntime() {
        return runtime;
    }

    @JsonProperty("runtime")
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @JsonProperty("premiered")
    public String getPremiered() {
        return premiered;
    }

    @JsonProperty("premiered")
    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    @JsonProperty("officialSite")
    public Object getOfficialSite() {
        return officialSite;
    }

    @JsonProperty("officialSite")
    public void setOfficialSite(Object officialSite) {
        this.officialSite = officialSite;
    }

    @JsonProperty("schedule")
    public Schedule getSchedule() {
        return schedule;
    }

    @JsonProperty("schedule")
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @JsonProperty("rating")
    public Rating getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @JsonProperty("weight")
    public int getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @JsonProperty("network")
    public Network getNetwork() {
        return network;
    }

    @JsonProperty("network")
    public void setNetwork(Network network) {
        this.network = network;
    }

    @JsonProperty("webChannel")
    public Object getWebChannel() {
        return webChannel;
    }

    @JsonProperty("webChannel")
    public void setWebChannel(Object webChannel) {
        this.webChannel = webChannel;
    }

    @JsonProperty("externals")
    public Externals getExternals() {
        return externals;
    }

    @JsonProperty("externals")
    public void setExternals(Externals externals) {
        this.externals = externals;
    }

    @JsonProperty("image")
    public Image getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Image image) {
        this.image = image;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("updated")
    public int getUpdated() {
        return updated;
    }

    @JsonProperty("updated")
    public void setUpdated(int updated) {
        this.updated = updated;
    }

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("_embedded")
    public Embedded getEmbedded() {
        return embedded;
    }

    @JsonProperty("_embedded")
    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("url", url).append("name", name).append("type", type).append("language", language).append("genres", genres).append("status", status).append("runtime", runtime).append("premiered", premiered).append("officialSite", officialSite).append("schedule", schedule).append("rating", rating).append("weight", weight).append("network", network).append("webChannel", webChannel).append("externals", externals).append("image", image).append("summary", summary).append("updated", updated).append("links", links).append("embedded", embedded).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(summary).append(weight).append(officialSite).append(genres).append(status).append(image).append(runtime).append(links).append(externals).append(type).append(embedded).append(network).append(url).append(id).append(schedule).append(updated).append(name).append(premiered).append(rating).append(language).append(webChannel).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TvMaze) == false) {
            return false;
        }
        TvMaze rhs = ((TvMaze) other);
        return new EqualsBuilder().append(summary, rhs.summary).append(weight, rhs.weight).append(officialSite, rhs.officialSite).append(genres, rhs.genres).append(status, rhs.status).append(image, rhs.image).append(runtime, rhs.runtime).append(links, rhs.links).append(externals, rhs.externals).append(type, rhs.type).append(embedded, rhs.embedded).append(network, rhs.network).append(url, rhs.url).append(id, rhs.id).append(schedule, rhs.schedule).append(updated, rhs.updated).append(name, rhs.name).append(premiered, rhs.premiered).append(rating, rhs.rating).append(language, rhs.language).append(webChannel, rhs.webChannel).isEquals();
    }

}
