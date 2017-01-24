import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.jsoup.Jsoup;

import java.io.File;
import java.util.*;

/**
 * Created by phonik on 2017-01-12.
 */
public class TvShowSummary {
    private int tvMazeId;
    private String tvMazeUrl;
    private String title;
    private String type;
    private String language;
    private List<String> genres;
    private String status;
    private int runtime;
    private Date premiered;
    private Double ratingAvg;
    private String network;
    private String country;
    private String imdbLink;
    private int thetvdbLink;
    private int tvrageLink;
    private String posterMed;
    private String posterOrg;
    private String summary;
    private List<TvShowEpisode> episodes;

    public TvShowSummary() {
        this.episodes = new ArrayList<TvShowEpisode>();
    }

    public List<TvShowEpisode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(TvShowEpisode episodes) {
        this.episodes.add(episodes);
    }

    public int getTvMazeId() {
        return tvMazeId;
    }

    public void setTvMazeId(int tvMazeId) {
        this.tvMazeId = tvMazeId;
    }

    public String getTvMazeUrl() {
        return tvMazeUrl;
    }

    public void setTvMazeUrl(String tvMazeUrl) {
        this.tvMazeUrl = tvMazeUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List getGenres() {
        return genres;
    }

    public void setGenres(List genres) {
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

    public Date getPremiered() {
        return premiered;
    }

    public void setPremiered(Date premiered) {
        this.premiered = premiered;
    }

    public Double getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(Double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }

    public int getThetvdbLink() {
        return thetvdbLink;
    }

    public void setThetvdbLink(int thetvdbLink) {
        this.thetvdbLink = thetvdbLink;
    }

    public int getTvrageLink() {
        return tvrageLink;
    }

    public void setTvrageLink(int tvrageLink) {
        this.tvrageLink = tvrageLink;
    }

    public String getPosterMed() {
        return posterMed;
    }

    public void setPosterMed(String posterMed) {
        this.posterMed = posterMed;
    }

    public String getPosterOrg() {
        return posterOrg;
    }

    public void setPosterOrg(String posterOrg) {
        this.posterOrg = posterOrg;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "TvShowSummary{" +
                "tvMazeId=" + tvMazeId +
                ", tvMazeUrl='" + tvMazeUrl + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", language='" + language + '\'' +
                ", genres=" + genres.toString() +
                ", status='" + status + '\'' +
                ", runtime='" + runtime + '\'' +
                ", premiered='" + premiered + '\'' +
                ", ratingAvg=" + ratingAvg +
                ", network='" + network + '\'' +
                ", country=" + country + '\'' +
                ", imdbLink='" + imdbLink + '\'' +
                ", thetvdbLink='" + thetvdbLink + '\'' +
                ", tvrageLink='" + tvrageLink + '\'' +
                ", posterMed='" + posterMed + '\'' +
                ", posterOrg='" + posterOrg + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
