package com.pewa.config;


import com.pewa.common.Results;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConfigFactoryTest {



    @Disabled
    @Test
    public void getConfigFactoryTest() {
        String x = ConfigFactory.get("item.omdbLink");
        System.out.println(x);
    }

    @Disabled
    @Test
    @DisplayName("Check if method throws exception when provided with non existent key")
    public void wrongConfigKey() {
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