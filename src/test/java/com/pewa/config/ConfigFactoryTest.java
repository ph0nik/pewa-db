package com.pewa.config;


import com.pewa.PewaType;
import com.pewa.common.AbstractMediaDAO;
import com.pewa.common.Results;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ConfigFactoryTest {



    @Disabled
    @Test
    public void getConfigFactoryTest() throws Exception {
        String x = ConfigFactory.get("item.omdbLink");
        System.out.println(x);
    }

    @Disabled
    @Test
    @DisplayName("Check if method throws exception when provided with non existent key")
    public void wrongConfigKey() {
//        assertThrows(NoSuchElementException.class, () -> {
//            ConfigFactory.get("nonexistent.key");
//        });
        String s = ConfigFactory.get("nonexistent.key");
        System.out.println(s);
    }

    @Disabled
    @Test
    public void emptyMapperMyBatis() {
        AbstractMediaTest abstractMediaTest = new AbstractMediaTest();
        Results results = abstractMediaTest.getObject(null);
        System.out.println(results);
    }


}