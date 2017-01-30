import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by phonik on 2017-01-12.
 */
public class Movie {
    private String title;
    private int year;
    private String ageRating;
    private String relDate;
    private String runtime;
    private String[] genre;
    private List<List<String>> crew;
    private String[] director;
    private String[] writer;
    private String[] actors;
    private String plot;
    private String[] language;
    private String[] country;
    private String awards;
    private String poster;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;

    public void peopleAsList() {
        List<List<String>> output = new ArrayList();
        for(String single : this.director) {
            List temp = Arrays.asList(single, "director");
            output.addAll(temp);
        }
        for(String single : this.actors) {
            List temp = Arrays.asList(single, "actor");
            output.addAll(temp);
        }
        for(String single : this.actors) {
            List temp = Arrays.asList(single, "writer");
            output.addAll(temp);
        }
        this.crew = output;
    }

  /*  public void sample(String title,
                       int year,
                       String ageRating,
                       String relDate,
                       String runtime,
                       String[] genre,
                       List people,
                       String plot,
                       String[] language,
                       String[] country,
                       String awards,
                       String poster,
                       String metascore,
                       String imdbRating,
                       String imdbVotes,
                       int imdbID) {
        this.title = title;
        this.year = year;
        this.ageRating = ageRating;
        this.relDate = relDate;
        this.runtime = runtime;
        this.genre = genre;
    }*/

    public List<List<String>> getCrew() {
        return crew;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public String getRelDate() {
        return relDate;
    }

    public void setRelDate(String relDate) {
        this.relDate = relDate;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String[] getDirector() {
        return director;
    }

    public void setDirector(String[] director) {
        this.director = director;
    }

    public String[] getWriter() {
        return writer;
    }

    public void setWriter(String[] writer) {
        this.writer = writer;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String[] getLanguage() {
        return language;
    }

    public void setLanguage(String[] language) {
        this.language = language;
    }

    public String[] getCountry() {
        return country;
    }

    public void setCountry(String[] country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", ageRating='" + ageRating + '\'' +
                ", relDate='" + relDate + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre=" + Arrays.toString(genre) +
                ", director=" + Arrays.toString(director) +
                ", writer=" + Arrays.toString(writer) +
                ", actors=" + Arrays.toString(actors) +
                ", plot='" + plot + '\'' +
                ", language=" + Arrays.toString(language) +
                ", country=" + Arrays.toString(country) +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                ", metascore='" + metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbID='" + imdbID + '\'' +
                '}';
    }
}
