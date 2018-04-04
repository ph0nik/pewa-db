package com.pewa.controller;

import com.pewa.common.Results;
import com.pewa.movie.MovieDAO;
import com.pewa.movie.MovieSearch;
import com.pewa.util.GlobalSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search/")
public class SearchController {

    @Autowired
    private GlobalSearch globalSearch;


    @GetMapping(value = "external/{query}")
    public Results searchExternal(@PathVariable String query) {
        return globalSearch
                .itemsExternalByTitle(query, new Results())
                .setReturnMessage();
    }

    @GetMapping(value = "internal/{query}")
    public Results searchDb(@PathVariable String query) {
        return globalSearch
                .itemsInternalByTitle(query)
                .setReturnMessage();
    }
}
