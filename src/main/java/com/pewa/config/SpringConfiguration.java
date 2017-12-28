package com.pewa.config;

import com.pewa.util.TmdbConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;

/**
 * Created by phonik on 2017-04-09.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.pewa"})
public class SpringConfiguration extends WebMvcConfigurerAdapter {

//    private final String allowedAddress = "http://localhost:8080";
    private final String allowedAddress = "*";
    private final String uriScope = "/**";

    public static final Logger log = LogManager.getLogger(SpringConfiguration.class);
    private final String trayStatusOK = "Tray loaded properly";

/*    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/static/");
        resolver.setSuffix(".jsp");
        return resolver;
    }*/

    /*
    * konfiguracja dostępów do elementów statycznych
    * adresy muszą wskazywać na foldery będące wewnątrz webapp (to jest folder nadrzędny)
    *
    * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    // TODO
    /*
    * załadowanie traya, ikona jest niewidoczna, sprawdzić czy plik się właściwie ładuje
    * akcja reaguje tylko na dwuklik myszy
    *
    * */
    @PostConstruct
    public void initSever() {
        PewaSystemTray.loadTray();
        log.debug(trayStatusOK);
    }

    @PostConstruct
    public void initTmdbConfig() {
        TmdbConfiguration runConfig = new TmdbConfiguration();
        runConfig.timerConfiguration(7);
    }

    /*
    * konfiguracja CORS pozwalająca na zapytania w ramach tej samej domeny
    */

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(uriScope).allowedOrigins(allowedAddress);
            }
        };
    }

}
