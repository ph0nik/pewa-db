package com.pewa.movie;

import com.pewa.common.SingleSearchResult;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by phonik on 2017-04-07.
 */
@Component
public interface MovieSearch {

    Set<SingleSearchResult> externalMovieSearch(String query);
}
