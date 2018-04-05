package com.pewa.util;

import com.pewa.common.Results;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalSearchTest {

    private GlobalSearch globalSearch;

    @BeforeEach
    public void initTest() {
        globalSearch = new GlobalSearch();
    }

    @Disabled
    @Test
    public void searchAll() {
        Results results = globalSearch.itemsInternalByTitle("n");
        System.out.println(results.toString());
    }
}