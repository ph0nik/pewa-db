package com.pewa.config;

import com.pewa.PewaType;
import com.pewa.common.AbstractMediaDAO;
import com.pewa.common.Results;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbstractMediaTest extends AbstractMediaDAO {

    private List<String> mapperList;

    public AbstractMediaTest() {
        super(PewaType.STATUS);
        mapperList = new ArrayList<>();
    }


    public Results getObject(String query) {
        mapperList = Arrays.asList("non-existent.key-mapper");
        return search(query);
    }

    @Override
    public List<String> getMapperList() {
        return mapperList;
    }

    @Override
    public String getInfoField() {
        return null;
    }
}
