[33mcommit 525e191ee6261fd553832b428c31e1cef5c66c13[m
Author: phonik <phonik83@gmail.com>
Date:   Tue Jan 23 12:02:05 2018 +0100

    ISO 639-1 language code reader changed in TMDB parser module.

[1mdiff --git a/src/main/java/com/pewa/Main.java b/src/main/java/com/pewa/Main.java[m
[1mindex 8af67e4..a16d7db 100644[m
[1m--- a/src/main/java/com/pewa/Main.java[m
[1m+++ b/src/main/java/com/pewa/Main.java[m
[36m@@ -1,6 +1,8 @@[m
 package com.pewa;[m
 [m
[32m+[m[32mimport com.neovisionaries.i18n.LanguageAlpha3Code;[m
 import org.apache.ibatis.io.Resources;[m
[32m+[m[32mimport org.apache.ibatis.session.ExecutorType;[m
 import org.apache.ibatis.session.SqlSession;[m
 import org.apache.ibatis.session.SqlSessionFactory;[m
 import org.apache.ibatis.session.SqlSessionFactoryBuilder;[m
[36m@@ -17,6 +19,16 @@[m [mimport java.util.TreeSet;[m
 public class Main {[m
     public static void main(String[] args) {[m
 [m
[32m+[m[32m        String bo = "";[m
[32m+[m[32m    try {[m
[32m+[m[32m        bo = LanguageAlpha3Code.getByCodeIgnoreCase("jasda").getName();[m
[32m+[m[32m    } catch (NullPointerException ex) {[m
[32m+[m[32m        bo = "[" + ex.toString() + "] @ " + LanguageAlpha3Code.class;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m
[32m+[m[32m        System.out.println(bo);[m
[32m+[m
    /*     Logger logger = LogManager.getLogger(Main.class);[m
 [m
         Exception ex = new Exception();[m
[1mdiff --git a/src/main/java/com/pewa/anime/AnimeMangaSearch.java b/src/main/java/com/pewa/anime/AnimeMangaSearch.java[m
[1mindex e50238b..dfe7685 100644[m
[1m--- a/src/main/java/com/pewa/anime/AnimeMangaSearch.java[m
[1m+++ b/src/main/java/com/pewa/anime/AnimeMangaSearch.java[m
[36m@@ -101,12 +101,8 @@[m [mpublic class AnimeMangaSearch {[m
                         : singleElement.getString("description","");[m
                 singleSearchResult.setType(searchType);[m
             }[m
[31m-            String title = new StringBuilder(titleRom)[m
[31m-                    .append(" [")[m
[31m-                    .append(titleEng)[m
[31m-                    .append("]")[m
[31m-                    .toString();[m
[31m-            singleSearchResult.setTitle(title);[m
[32m+[m[32m            singleSearchResult.setTitle(titleRom);[m
[32m+[m[32m            singleSearchResult.setAltTitle(titleEng);[m
             singleSearchResult.setDescription(addInfo);[m
             singleSearchResult.setIdInt(id);[m
             singleSearchResult.setDate(zdt);[m
[1mdiff --git a/src/main/java/com/pewa/common/EncounterElement.java b/src/main/java/com/pewa/common/EncounterElement.java[m
[1mindex 022b8d0..7dabba7 100644[m
[1m--- a/src/main/java/com/pewa/common/EncounterElement.java[m
[1m+++ b/src/main/java/com/pewa/common/EncounterElement.java[m
[36m@@ -14,7 +14,7 @@[m [mimport java.util.Set;[m
 public class EncounterElement implements Comparable<EncounterElement>, Serializable, Encounter{[m
 //    <!-- id, title, year, type, status -> id, encDate, addDate, rating -->[m
     private Integer id;[m
[31m-    private String title;[m
[32m+[m[32m    private String title, engTitle;[m
     private Integer year;[m
     private PewaType type;[m
     private Set<Status> status;[m
[36m@@ -28,6 +28,7 @@[m [mpublic class EncounterElement implements Comparable<EncounterElement>, Serializa[m
 [m
         if (id != null ? !id.equals(that.id) : that.id != null) return false;[m
         if (title != null ? !title.equals(that.title) : that.title != null) return false;[m
[32m+[m[32m        if (engTitle != null ? !engTitle.equals(that.engTitle) : that.engTitle != null) return false;[m
         if (year != null ? !year.equals(that.year) : that.year != null) return false;[m
         if (type != that.type) return false;[m
         return status != null ? status.equals(that.status) : that.status == null;[m
[36m@@ -37,6 +38,7 @@[m [mpublic class EncounterElement implements Comparable<EncounterElement>, Serializa[m
     public int hashCode() {[m
         int result = id != null ? id.hashCode() : 0;[m
         result = 31 * result + (title != null ? title.hashCode() : 0);[m
[32m+[m[32m        result = 31 * result + (engTitle != null ? engTitle.hashCode() : 0);[m
         result = 31 * result + (year != null ? year.hashCode() : 0);[m
         result = 31 * result + (type != null ? type.hashCode() : 0);[m
         result = 31 * result + (status != null ? status.hashCode() : 0);[m
[36m@@ -48,12 +50,21 @@[m [mpublic class EncounterElement implements Comparable<EncounterElement>, Serializa[m
         return "EncounterElement{" +[m
                 "id=" + id +[m
                 ", title='" + title + '\'' +[m
[32m+[m[32m                ", titleEng='" + engTitle + '\'' +[m
                 ", year=" + year +[m
                 ", type=" + type +[m
                 ", status=" + status +[m
                 '}';[m
     }[m
 [m
[32m+[m[32m    public String getEngTitle() {[m
[32m+[m[32m        return engTitle;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setEngTitle(String titleEng) {[m
[32m+[m[32m        this.engTitle = titleEng;[m
[32m+[m[32m    }[m
[32m+[m
     public Integer getId() {[m
         return id;[m
     }[m
[1mdiff --git a/src/main/java/com/pewa/common/SingleSearchResult.java b/src/main/java/com/pewa/common/SingleSearchResult.java[m
[1mindex ba5270a..6f3d7a9 100644[m
[1m--- a/src/main/java/com/pewa/common/SingleSearchResult.java[m
[1m+++ b/src/main/java/com/pewa/common/SingleSearchResult.java[m
[36m@@ -13,7 +13,7 @@[m [mimport java.util.Comparator;[m
 @JsonInclude(JsonInclude.Include.NON_EMPTY)[m
 public class SingleSearchResult implements Encounter, Comparable<SingleSearchResult> {[m
     private String url;[m
[31m-    private String title, description;[m
[32m+[m[32m    private String title, description, altTitle;[m
     private Integer idInt;[m
     private String poster;[m
     private PewaType type;[m
[36m@@ -21,6 +21,14 @@[m [mpublic class SingleSearchResult implements Encounter, Comparable<SingleSearchRes[m
     @JsonSerialize(using = CustomLocalDateSerializer.class)[m
     private LocalDate date;[m
 [m
[32m+[m[32m    public String getAltTitle() {[m
[32m+[m[32m        return altTitle;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setAltTitle(String altTitle) {[m
[32m+[m[32m        this.altTitle = altTitle;[m
[32m+[m[32m    }[m
[32m+[m
     public LocalDate getDate() {[m
         return date;[m
     }[m
[36m@@ -83,6 +91,7 @@[m [mpublic class SingleSearchResult implements Encounter, Comparable<SingleSearchRes[m
                 "url='" + url + '\'' +[m
                 ", title='" + title + '\'' +[m
                 ", description='" + description + '\'' +[m
[32m+[m[32m                ", altTitle='" + altTitle + '\'' +[m
                 ", idInt=" + idInt +[m
                 ", poster='" + poster + '\'' +[m
                 ", type=" + type +[m
[36m@@ -100,6 +109,7 @@[m [mpublic class SingleSearchResult implements Encounter, Comparable<SingleSearchRes[m
         if (url != null ? !url.equals(that.url) : that.url != null) return false;[m
         if (title != null ? !title.equals(that.title) : that.title != null) return false;[m
         if (description != null ? !description.equals(that.description) : that.description != null) return false;[m
[32m+[m[32m        if (altTitle != null ? !altTitle.equals(that.altTitle) : that.altTitle != null) return false;[m
         if (idInt != null ? !idInt.equals(that.idInt) : that.idInt != null) return false;[m
         if (poster != null ? !poster.equals(that.poster) : that.poster != null) return false;[m
         if (type != that.type) return false;[m
[36m@@ -111,6 +121,7 @@[m [mpublic class SingleSearchResult implements Encounter, Comparable<SingleSearchRes[m
         int result = url != null ? url.hashCode() : 0;[m
         result = 31 * result + (title != null ? title.hashCode() : 0);[m
         result = 31 * result + (description != null ? description.hashCode() : 0);[m
[32m+[m[32m        result = 31 * result + (altTitle != null ? altTitle.hashCode() : 0);[m
         result = 31 * result + (idInt != null ? idInt.hashCode() : 0);[m
         result = 31 * result + (poster != null ? poster.hashCode() : 0);[m
         result = 31 * result + (type != null ? type.hashCode() : 0);[m
[1mdiff --git a/src/main/java/com/pewa/movie/Movie.java b/src/main/java/com/pewa/movie/Movie.java[m
[1mindex ebca1f5..bb56110 100644[m
[1m--- a/src/main/java/com/pewa/movie/Movie.java[m
[1m+++ b/src/main/java/com/pewa/movie/Movie.java[m
[36m@@ -24,7 +24,7 @@[m [mimport java.util.*;[m
 @JsonInclude(JsonInclude.Include.NON_EMPTY)[m
 public class Movie extends MediaModel implements Comparable<Movie>, Serializable, Encounter {[m
 [m
[31m-    private String title, titlePl, intPoster, plot;[m
[32m+[m[32m    private String title, titlePl, intPoster, plot, engTitle;[m
     @JsonIgnore[m
     private String poster;[m
     @JsonDeserialize(using = CustomLocalDateDeserializer.class)[m
[36m@@ -34,6 +34,7 @@[m [mpublic class Movie extends MediaModel implements Comparable<Movie>, Serializable[m
     @JsonSerialize(using = CustomLocalDateSerializer.class)[m
     private LocalDate relDatePl;[m
     private Integer year, id, runtime, tmdbId;[m
[32m+[m[32m    private Double tmdbRating;[m
 //    @JsonProperty(value = "StandardImdbId")[m
     @JsonIgnore[m
     private Integer imdbID;[m
[36m@@ -58,6 +59,21 @@[m [mpublic class Movie extends MediaModel implements Comparable<Movie>, Serializable[m
 //        this.status = new TreeSet<>();[m
     }[m
 [m
[32m+[m[32m    public String getEngTitle() {[m
[32m+[m[32m        return engTitle;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setEngTitle(String engTitle) {[m
[32m+[m[32m        this.engTitle = engTitle;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public Double getTmdbRating() {[m
[32m+[m[32m        return tmdbRating;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setTmdbRating(Double tmdbRating) {[m
[32m+[m[32m        this.tmdbRating = tmdbRating;[m
[32m+[m[32m    }[m
 [m
     public Integer getImdbID() {[m
         return imdbID;[m
[36m@@ -254,7 +270,6 @@[m [mpublic class Movie extends MediaModel implements Comparable<Movie>, Serializable[m
     }[m
 [m
 [m
[31m-[m
     @Override[m
     public String toString() {[m
         return "Movie{" +[m
[36m@@ -262,14 +277,17 @@[m [mpublic class Movie extends MediaModel implements Comparable<Movie>, Serializable[m
                 ", titlePl='" + titlePl + '\'' +[m
                 ", intPoster='" + intPoster + '\'' +[m
                 ", plot='" + plot + '\'' +[m
[32m+[m[32m                ", engTitle='" + engTitle + '\'' +[m
                 ", poster='" + poster + '\'' +[m
                 ", relDate=" + relDate +[m
                 ", relDatePl=" + relDatePl +[m
                 ", year=" + year +[m
                 ", id=" + id +[m
[31m-                ", imdbID=" + imdbID +[m
                 ", runtime=" + runtime +[m
                 ", tmdbId=" + tmdbId +[m
[32m+[m[32m                ", tmdbRating=" + tmdbRating +[m
[32m+[m[32m                ", imdbID=" + imdbID +[m
[32m+[m[32m                ", imdbLink='" + imdbLink + '\'' +[m
                 ", language=" + language +[m
                 ", country=" + country +[m
                 ", genres=" + genres +[m
[36m@@ -294,14 +312,18 @@[m [mpublic class Movie extends MediaModel implements Comparable<Movie>, Serializable[m
         if (title != null ? !title.equals(movie.title) : movie.title != null) return false;[m
         if (titlePl != null ? !titlePl.equals(movie.titlePl) : movie.titlePl != null) return false;[m
         if (intPoster != null ? !intPoster.equals(movie.intPoster) : movie.intPoster != null) return false;[m
[31m-        if (runtime != null ? !runtime.equals(movie.runtime) : movie.runtime != null) return false;[m
         if (plot != null ? !plot.equals(movie.plot) : movie.plot != null) return false;[m
[32m+[m[32m        if (engTitle != null ? !engTitle.equals(movie.engTitle) : movie.engTitle != null) return false;[m
         if (poster != null ? !poster.equals(movie.poster) : movie.poster != null) return false;[m
         if (relDate != null ? !relDate.equals(movie.relDate) : movie.relDate != null) return false;[m
         if (relDatePl != null ? !relDatePl.equals(movie.relDatePl) : movie.relDatePl != null) return false;[m
         if (year != null ? !year.equals(movie.year) : movie.year != null) return false;[m
         if (id != null ? !id.equals(movie.id) : movie.id != null) return false;[m
[32m+[m[32m        if (runtime != null ? !runtime.equals(movie.runtime) : movie.runtime != null) return false;[m
[32m+[m[32m        if (tmdbId != null ? !tmdbId.equals(movie.tmdbId) : movie.tmdbId != null) return false;[m
[32m+[m[32m        if (tmdbRating != null ? !tmdbRating.equals(movie.tmdbRating) : movie.tmdbRating != null) return false;[m
         if (imdbID != null ? !imdbID.equals(movie.imdbID) : movie.imdbID != null) return false;[m
[32m+[m[32m        if (imdbLink != null ? !imdbLink.equals(movie.imdbLink) : movie.imdbLink != null) return false;[m
         if (language != null ? !language.equals(movie.language) : movie.language != null) return false;[m
         if (country != null ? !country.equals(movie.country) : movie.country != null) return false;[m
         if (genres != null ? !genres.equals(movie.genres) : movie.genres != null) return false;[m
[36m@@ -316,14 +338,18 @@[m [mpublic class Movie extends MediaModel implements Comparable<Movie>, Serializable[m
         int result = title != null ? title.hashCode() : 0;[m
         result = 31 * result + (titlePl != null ? titlePl.hashCode() : 0);[m
         result = 31 * result + (intPoster != null ? intPoster.hashCode() : 0);[m
[31m-        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);[m
         result = 31 * result + (plot != null ? plot.hashCode() : 0);[m
[32m+[m[32m        result = 31 * result + (engTitle != null ? engTitle.hashCode() : 0);[m
         result = 31 * result + (poster != null ? poster.hashCode() : 0);[m
         result = 31 * result + (relDate != null ? relDate.hashCode() : 0);[m
         result = 31 * result + (relDatePl != null ? relDatePl.hashCode() : 0);[m
         result = 31 * result + (year != null ? year.hashCode() : 0);[m
         result = 31 * result + (id != null ? id.hashCode() : 0);[m
[32m+[m[32m        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);[m
[32m+[m[32m        result = 31 * result + (tmdbId != null ? tmdbId.hashCode() : 0);[m
[32m+[m[32m        result = 31 * result + (tmdbRating != null ? tmdbRating.hashCode() : 0);[m
         result = 31 * result + (imdbID != null ? imdbID.hashCode() : 0);[m
[32m+[m[32m        result = 31 * result + (imdbLink != null ? imdbLink.hashCode() : 0);[m
         result = 31 * result + (language != null ? language.hashCode() : 0);[m
         result = 31 * result + (country != null ? country.hashCode() : 0);[m
         result = 31 * result + (genres != null ? genres.hashCode() : 0);[m
[36m@@ -336,7 +362,7 @@[m [mpublic class Movie extends MediaModel implements Comparable<Movie>, Serializable[m
 [m
     @Override[m
     public int compareTo(Movie o) {[m
[31m-        return this.title.equals(o.title) ? this.year.compareTo(o.year) : this.getTitle().compareTo(o.getTitle());[m
[32m+[m[32m        return this.title.equals(o.title) ? this.tmdbId.compareTo(o.tmdbId) : this.getTitle().compareTo(o.getTitle());[m
     }[m
 [m
 }[m
[1mdiff --git a/src/main/java/com/pewa/movie/MovieParserTmdb.java b/src/main/java/com/pewa/movie/MovieParserTmdb.java[m
[1mindex a15f50e..f8b0fd5 100644[m
[1m--- a/src/main/java/com/pewa/movie/MovieParserTmdb.java[m
[1m+++ b/src/main/java/com/pewa/movie/MovieParserTmdb.java[m
[36m@@ -3,6 +3,7 @@[m [mpackage com.pewa.movie;[m
 import com.eclipsesource.json.Json;[m
 import com.eclipsesource.json.JsonObject;[m
 import com.google.gson.Gson;[m
[32m+[m[32mimport com.neovisionaries.i18n.LanguageAlpha3Code;[m
 import com.pewa.MediaParse;[m
 import com.pewa.PewaType;[m
 import com.pewa.common.*;[m
[36m@@ -64,6 +65,7 @@[m [mpublic class MovieParserTmdb implements MediaParse<Movie, Integer> {[m
         TmdbItem tmdbItem = gson.fromJson(responseJson, TmdbItem.class);[m
         movieitem.setImdbID(idImdbString2Int(tmdbItem.getImdbId()));[m
         movieitem.setTitle(tmdbItem.getOriginalTitle());[m
[32m+[m[32m        movieitem.setEngTitle(tmdbItem.getTitle());[m
         tmdbItem.getAlternativeTitles().getTitles().forEach(t -> {[m
             if (t.getIso31661().equals("PL")) movieitem.setTitlePl(t.getTitle());[m
         });[m
[36m@@ -77,12 +79,22 @@[m [mpublic class MovieParserTmdb implements MediaParse<Movie, Integer> {[m
             if (role.equals("director") || role.equals("screenplay") || role.equals("writer")|| role.equals("original music composer"))[m
                 movieitem.setStaff(new Person(s.getName(), "", role));[m
         });[m
[31m-        tmdbItem.getSpokenLanguages().forEach(l -> movieitem.setLanguage(new Language(l.getName())));[m
[32m+[m[32m        tmdbItem.getSpokenLanguages().forEach(l -> {[m
[32m+[m[32m            String languageNameByCode;[m
[32m+[m[32m            try {[m
[32m+[m[32m                languageNameByCode = LanguageAlpha3Code.getByCodeIgnoreCase(l.getIso6391()).getName();[m
[32m+[m[32m            } catch (NullPointerException ex) {[m
[32m+[m[32m                log.error("[" + ex.toString() + "] @ " + LanguageAlpha3Code.class);[m
[32m+[m[32m                languageNameByCode = l.getName();[m
[32m+[m[32m            }[m
[32m+[m[32m            movieitem.setLanguage(new Language(languageNameByCode));[m
[32m+[m[32m        });[m
         tmdbItem.getProductionCountries().forEach(c -> movieitem.setCountry(new Country(c.getName())));[m
         tmdbItem.getGenres().forEach(g -> movieitem.setGenres(new Genre(g.getName())));[m
         DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;[m
         LocalDate releaseDate = LocalDate.parse(tmdbItem.getReleaseDate(), formatter);[m
         movieitem.setRelDate(releaseDate);[m
[32m+[m[32m        movieitem.setTmdbRating(tmdbItem.getVoteAverage());[m
         movieitem.setYear(movieitem.getRelDate().getYear());[m
         movieitem.setType(PewaType.MOVIE);[m
         movieitem.setRuntime(tmdbItem.getRuntime());[m
[1mdiff --git a/src/main/java/com/pewa/movie/MovieSearchTheMovieDatabase.java b/src/main/java/com/pewa/movie/MovieSearchTheMovieDatabase.java[m
[1mindex 35eb204..287150c 100644[m
[1m--- a/src/main/java/com/pewa/movie/MovieSearchTheMovieDatabase.java[m
[1m+++ b/src/main/java/com/pewa/movie/MovieSearchTheMovieDatabase.java[m
[36m@@ -33,7 +33,6 @@[m [mpublic class MovieSearchTheMovieDatabase implements MovieSearch {[m
 [m
     public Results externalMovieSearch(String request, Results results) {[m
         Set<SingleSearchResult> searchResultSet = new TreeSet<>();[m
[31m-        //TODO przywrócić string w ciągu, bez sensowne pakowanie i wypakowywanie z obiektu request[m
         try {[m
             String url = new StringBuilder(ConfigFactory.get("themoviedb.url"))[m
                     .append(ConfigFactory.get("themoviedb.search"))[m
[36m@@ -68,6 +67,7 @@[m [mpublic class MovieSearchTheMovieDatabase implements MovieSearch {[m
             SingleSearchResult ssr = new SingleSearchResult();[m
             ssr.setIdInt(sr.getId());[m
             ssr.setTitle(sr.getOriginalTitle());[m
[32m+[m[32m            ssr.setAltTitle(sr.getTitle());[m
             if (sr.getPosterPath() != null) ssr.setPoster(sr.getPosterPath());[m
             if (sr.getReleaseDate().length() == 0)[m
                 ssr.setDate(LocalDate.now());[m
[1mdiff --git a/src/test/java/com/pewa/movie/MovieDAOImplTest.java b/src/test/java/com/pewa/movie/MovieDAOImplTest.java[m
[1mindex 185f643..835ad07 100644[m
[1m--- a/src/test/java/com/pewa/movie/MovieDAOImplTest.java[m
[1m+++ b/src/test/java/com/pewa/movie/MovieDAOImplTest.java[m
[36m@@ -46,21 +46,19 @@[m [mpublic class MovieDAOImplTest {[m
 [m
     }[m
 [m
[31m-//    termit - 103064[m
[31m-//    john wick -  2911666[m
[31m-    // evil aliens = 110383353[m
[31m-    // aliens - 110090605[m
[31m-    // cowboys vs aliens - 110409847[m
[32m+[m
[32m+[m[32m    // aliens - 679[m
[32m+[m[32m    // terminator - 218[m
     @Disabled[m
     @Test[m
     public void addMovie() {[m
[31m-        MediaParse<Movie, String> movieParser = new MovieParser();[m
[31m-        Movie movie = movieParser.getItem("tt0383353");[m
[32m+[m[32m        MediaParse<Movie, Integer> movieParser = new MovieParserTmdb();[m
[32m+[m[32m        Movie movie = movieParser.getItem(679);[m
         System.out.println(movie.toString());[m
         movieDAO.addMovie(movie, results);[m
         Status status = new Status();[m
         status.setElementType(PewaType.MOVIE);[m
[31m-        status.setComment("Bardzo fajny film");[m
[32m+[m[32m        status.setComment("Aliens");[m
         status.setEncounterDate(LocalDate.now());[m
         status.setMediaSource(MediaSource.COMPUTER);[m
         status.setEncounterRating(9);[m
[36m@@ -70,7 +68,7 @@[m [mpublic class MovieDAOImplTest {[m
     @Disabled[m
     @Test[m
     public void searchMovieByQuery(){[m
[31m-        String req = "alien";[m
[32m+[m[32m        String req = "blade";[m
         Results movie = movieDAO.moviesByTitle(req, results);[m
         System.out.println(movie);[m
     }[m
[36m@@ -78,7 +76,7 @@[m [mpublic class MovieDAOImplTest {[m
     @Test[m
     public void updateMovie(){[m
         MediaParse<Movie, Integer> movieParser = new MovieParserTmdb();[m
[31m-        Integer request = 218;[m
[32m+[m[32m        Integer request = 679;[m
         Movie americanBuffalo = movieParser.getItem(request);[m
 //        System.out.println(americanBuffalo.toString());[m
         Results results = movieDAO.updMovie(americanBuffalo, new Results());[m
[36m@@ -87,13 +85,14 @@[m [mpublic class MovieDAOImplTest {[m
     @Disabled[m
     @Test[m
     public void getMovieById(){[m
[31m-        Integer request = 7;[m
[32m+[m[32m        Integer request = 6;[m
         Results sampleMovie = movieDAO.moviesById(request, results);[m
         Movie movie = (Movie) sampleMovie.getEncounters().get(0);[m
 [m
         System.out.println(movie);[m
 //        sampleMovie.getStaff().forEach(System.out::println);[m
     }[m
[32m+[m
     @Disabled[m
     @Test[m
     public void searchMovieByPerson() {[m
[1mdiff --git a/src/test/java/com/pewa/movie/MovieSearchTheMovieDatabaseTest.java b/src/test/java/com/pewa/movie/MovieSearchTheMovieDatabaseTest.java[m
[1mindex 29fab9e..925a5b5 100644[m
[1m--- a/src/test/java/com/pewa/movie/MovieSearchTheMovieDatabaseTest.java[m
[1m+++ b/src/test/java/com/pewa/movie/MovieSearchTheMovieDatabaseTest.java[m
[36m@@ -48,7 +48,7 @@[m [mclass MovieSearchTheMovieDatabaseTest {[m
     public void getMovieDetails() {[m
         MediaParse<Movie, Integer> getMovie = new MovieParserTmdb();[m
         Request request = new Request();[m
[31m-        Integer movieid = 105;[m
[32m+[m[32m        Integer movieid = 426284;[m
         Movie movie = getMovie.getItem(movieid); // Aliens[m
         System.out.println(movie);[m
     }[m
