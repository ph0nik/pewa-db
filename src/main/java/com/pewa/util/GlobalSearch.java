package com.pewa.util;

import com.pewa.PewaType;
import com.pewa.anime.*;
import com.pewa.book.BookDAO;
import com.pewa.book.BookDAOImpl;
import com.pewa.book.BookSearch;
import com.pewa.common.EncounterElement;
import com.pewa.common.Results;
import com.pewa.config.ConfigFactory;
import com.pewa.dao.MyBatisFactory;
import com.pewa.movie.MovieDAO;
import com.pewa.movie.MovieDAOImpl;
import com.pewa.movie.MovieSearch;
import com.pewa.movie.MovieSearchTheMovieDatabase;
import com.pewa.movie.tmdb.Result;
import com.pewa.tv.TvShowDAO;
import com.pewa.tv.TvShowDAOImpl;
import com.pewa.tv.TvShowSearch;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GlobalSearch {

    private static final Logger log = LogManager.getLogger(GlobalSearch.class);
    private List<EncounterElement> output;
    private final PewaType animeType = PewaType.ANIME;
    private final PewaType mangaType = PewaType.MANGA;
    private final PewaType bookType = PewaType.BOOK;
    private final PewaType tvType = PewaType.TVSERIES;
    private MovieSearch movieSearch;
    private AnimeMangaSearch animeMangaSearch;
    private TvShowSearch tvShowSearch;
    private BookSearch bookSearch;
    private AnimeDAO anime;
    private MangaDAO manga;
    private MovieDAO movie;
    private TvShowDAO tvshow;
    private BookDAO book;

    public GlobalSearch() {
        anime = new AnimeDAOImpl();
        manga = new MangaDAOImpl();
        movie = new MovieDAOImpl();
        tvshow = new TvShowDAOImpl();
        book = new BookDAOImpl();
    }

    public Results itemsInternalByTitle(String search) {
        Results combinedResults = new Results();
        anime.getAnimeByTitle(search)
                .getEncounters()
                .forEach(combinedResults::setEncounters);
        manga.getMangaByTitle(search)
                .getEncounters()
                .forEach(combinedResults::setEncounters);
        movie.moviesByTitle(search)
                .getEncounters()
                .forEach(combinedResults::setEncounters);
        book.getBook(search)
                .getEncounters()
                .forEach(combinedResults::setEncounters);
        tvshow.tvshowByTitle(search)
                .getEncounters()
                .forEach(combinedResults::setEncounters);
//        try (SqlSession session = MyBatisFactory.connectionUser().openSession(ExecutorType.SIMPLE, false)) {
//            String query = new StringBuilder("%")
//                    .append(search)
//                    .append("%")
//                    .toString();
//            output = session.selectList(ConfigFactory.get("movie-mapper.byTitleMovie"), query);
//            output.forEach(results::setEncounters);
//            output = session.selectList(ConfigFactory.get("anime-mapper.byTitleAni"), query);
//            output.forEach(x -> x.setType(animeType));
//            output.forEach(results::setEncounters);
//            output = session.selectList(ConfigFactory.get("manga-mapper.byTitleMan"), query);
//            output.forEach(x -> x.setType(mangaType));
//            output.forEach(results::setEncounters);
//            output = session.selectList(ConfigFactory.get("book-mapper.byTitle"), query);
//            output.forEach(x -> x.setType(bookType));
//            output.forEach(results::setEncounters);
//            output = session.selectList(ConfigFactory.get("tv-mapper.byTitleTv"), query);
//            output.forEach(x -> x.setType(tvType));
//            output.forEach(results::setEncounters);
//            session.commit();
//        }
        return combinedResults;
    }

    public Results itemsExternalByTitle(String search, Results results) {
        movieSearch = new MovieSearchTheMovieDatabase();
        results = new Results();
        Results partialResults = movieSearch.externalMovieSearch(search, new Results());
        results.add(partialResults);

        animeMangaSearch = new AnimeMangaSearch();
        partialResults = animeMangaSearch.aniListSearchV2(search, PewaType.ANIME);
        results.add(partialResults);

        tvShowSearch = new TvShowSearch();
        partialResults = tvShowSearch.searchTv(search, new Results());
        results.add(partialResults);

        bookSearch = new BookSearch();
        partialResults = bookSearch.bookSearchResultSet(search, new Results());
        results.add(partialResults);

        partialResults = animeMangaSearch.aniListSearchV2(search, PewaType.MANGA);
        results.add(partialResults);

        return results;
    }
}
