package com.pewa.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ConfigFactory extends HierarchicalINIConfiguration {
    private static final String CONFIG_FILE = "src/main/resources/pewa-config.ini";
    private static ConfigFactory config = null;
    private static final Logger log = LogManager.getLogger(ConfigFactory.class);


    private ConfigFactory(File file) throws ConfigurationException {
        super(file);
    }

    public static ConfigFactory getConfigFactory() {
        try {
            if (config == null) {
                File configFile = new File(CONFIG_FILE);
                config = new ConfigFactory(configFile);
                config.setThrowExceptionOnMissing(true);
            } else {
                config.refresh();
            }
        } catch (ConfigurationException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
        return config;
    }
}
