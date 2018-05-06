package com.pewa.movie;

import com.pewa.common.Results;
import org.springframework.stereotype.Component;

/**
 * Created by phonik on 2017-04-07.
 */
@Component
public interface MovieSearch {

    Results externalMovieSearch(String query, Results results);
}
