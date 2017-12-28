package com.pewa.config;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class ConfigFactory extends INIConfiguration {
    private static final String CONFIG_FILE = "src/main/resources/pewa-config.ini";
    private static ConfigFactory config = null;

    private static final Logger log = LogManager.getLogger(ConfigFactory.class);

    private ConfigFactory() {
        super();
    }

    private static void initConfig() {
        try {
            if (config == null) {
                File configFile = new File(CONFIG_FILE);
                Reader fileReader = new BufferedReader(new FileReader(configFile));
                config = new ConfigFactory();
                config.read(fileReader);
                config.setThrowExceptionOnMissing(true);
            }
        } catch (ConfigurationException | IOException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
    }

    public static String get(String param) {
//        try {
//            if (config == null) {
//                File configFile = new File(CONFIG_FILE);
//                Reader fileReader = new BufferedReader(new FileReader(configFile));
//                config = new ConfigFactory();
//                config.read(fileReader);
//                config.setThrowExceptionOnMissing(true);
//            }
//        } catch (ConfigurationException | IOException e) {
//            log.error(e.getMessage(), e.fillInStackTrace());
//        }
        initConfig();
        return config.getString(param);
    }

    public static SubnodeConfiguration getIniSection(String name){
        initConfig();
        return config.getSection(name);
    }
/*    public static ConfigFactory update() {
        try {
            config.refresh();
        } catch (ConfigurationException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
        return config;
    }*/
}


/*
public class ConfigFactory extends HierarchicalINIConfiguration {
    private static final String CONFIG_FILE = "src/main/resources/pewa-config.ini";
    private static ConfigFactory config = null;

    private static final Logger log = LogManager.getLogger(ConfigFactory.class);

    private ConfigFactory(File file) throws ConfigurationException {
        super(file);
    }

    public static String get(String param) {
        try {
            if (config == null) {
                File configFile = new File(CONFIG_FILE);
                config = new ConfigFactory(configFile);
                config.setThrowExceptionOnMissing(true);
            }
        } catch (ConfigurationException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
        return config.getString(param);
    }

    public static ConfigFactory update() {
        try {
            config.refresh();
        } catch (ConfigurationException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
        return config;
    }
}
*/
