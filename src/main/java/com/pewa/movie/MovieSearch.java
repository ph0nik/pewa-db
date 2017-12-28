package com.pewa.movie;

import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.common.SingleSearchResult;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by phonik on 2017-04-07.
 */
@Component
public interface MovieSearch {

    Results externalMovieSearch(String query, Results results);

    Integer idImdbString2Int(String idImdb);
}
