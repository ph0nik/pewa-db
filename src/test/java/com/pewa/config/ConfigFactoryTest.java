package com.pewa.config;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ConfigFactoryTest {
    @Disabled
    @Test
    public void getConfigFactoryTest() throws Exception {
        String x = ConfigFactory.get("item.omdbLink");
        System.out.println(x);
    }

}