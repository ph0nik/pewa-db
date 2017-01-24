import java.text.DateFormat;
import java.util.Date;

/**
 * Created by phonik on 2017-01-13.
 */
public class TvShowEpisode {
    private int tvMazeId;
    private String tvMazeUrl;
    private String epTitle;
    private int season;
    private int episode;
    private Date firstAired;
    private String summary;

    public TvShowEpisode(int tvMazeId, String tvMazeUrl, String epTitle, int season, int episode, Date firstAired, String summary) {
        this.tvMazeId = tvMazeId;
        this.tvMazeUrl = tvMazeUrl;
        this.epTitle = epTitle;
        this.season = season;
        this.episode = episode;
        this.firstAired = firstAired;
        this.summary = summary;
    }

    public int getTvMazeId() {
        return tvMazeId;
    }

    public String getTvMazeUrl() {
        return tvMazeUrl;
    }

    public String getEpTitle() {
        return epTitle;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }

    public Date getFirstAired() {
        return firstAired;
    }

    public String getSummary() {
        return summary;
    }

    @Override
    public String toString() {
        return "TvShowEpisode{" +
                "tvMazeId=" + tvMazeId +
                ", tvMazeUrl='" + tvMazeUrl + '\'' +
                ", epTitle='" + epTitle + '\'' +
                ", season=" + season +
                ", episode=" + episode +
                ", firstAired=" + firstAired +
                ", summary='" + summary + '\'' +
                '}';
    }
}
