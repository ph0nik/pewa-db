package com.pewa.tv;

import com.pewa.common.Results;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import java.util.Set;

/**
 * Created by phonik on 2017-03-31.
 */
public class TvShowSearchTest {
    @Disabled
    @Test
    public void searchTv() {
        TvShowSearch searchTv = new TvShowSearch();
        Results summary = searchTv.searchTv("alien", new Results());
        summary.getEncounters().forEach(System.out::println);

    }

}