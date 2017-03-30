package com.pewa.config;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigFactoryTest {
    @Test
    public void getConfigFactoryTest() throws Exception {
        String x = ConfigFactory.getConfigFactory().getString("item.omdbLink");
        System.out.println(x);
    }

}