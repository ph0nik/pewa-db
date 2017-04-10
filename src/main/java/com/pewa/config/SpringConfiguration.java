package com.pewa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by phonik on 2017-04-09.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.pewa"})
public class SpringConfiguration /*extends WebMvcConfigurerAdapter*/ {
}
