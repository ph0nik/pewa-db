
package com.pewa.tv.tvmaze;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TvMaze {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("runtime")
    @Expose
    private int runtime;
    @SerializedName("premiered")
    @Expose
    private String premiered;
    @SerializedName("officialSite")
    @Expose
    private String officialSite;
    @SerializedName("schedule")
    @Expose
    private Schedule schedule;
    @SerializedName("rating")
    @Expose
    private Rating rating;
    @SerializedName("weight")
    @Expose
    private int weight;
    @SerializedName("network")
    @Expose
    private Network network;
    @SerializedName("webChannel")
    @Expose
    private WebChannel webChannel;
    @SerializedName("externals")
    @Expose
    private Externals externals;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("updated")
    @Expose
    private int updated;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public WebChannel getWebChannel() {
        return webChannel;
    }

    public void setWebChannel(WebChannel webChannel) {
        this.webChannel = webChannel;
    }

    public Externals getExternals() {
        return externals;
    }

    public void setExternals(Externals externals) {
        this.externals = externals;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

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
        if (!(other instanceof TvMaze)) {
            return false;
        }
        TvMaze rhs = ((TvMaze) other);
        return new EqualsBuilder().append(summary, rhs.summary).append(weight, rhs.weight).append(officialSite, rhs.officialSite).append(genres, rhs.genres).append(status, rhs.status).append(image, rhs.image).append(runtime, rhs.runtime).append(links, rhs.links).append(externals, rhs.externals).append(type, rhs.type).append(embedded, rhs.embedded).append(network, rhs.network).append(url, rhs.url).append(id, rhs.id).append(schedule, rhs.schedule).append(updated, rhs.updated).append(name, rhs.name).append(premiered, rhs.premiered).append(rating, rhs.rating).append(language, rhs.language).append(webChannel, rhs.webChannel).isEquals();
    }

}
