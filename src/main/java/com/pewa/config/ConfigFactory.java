package com.pewa.config;

import com.pewa.exceptions.EmptyMapperException;
import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ConfigFactory extends INIConfiguration {
    //    private static final String CONFIG_FILE = "src/main/resources/pewa-config.ini";
    private static final String CONFIG_FILE = "pewa-config.ini";
    private static ConfigFactory config = null;

    private static final Logger log = LogManager.getLogger(ConfigFactory.class);

    private ConfigFactory() {
        super();
    }

    /**
     * Loads file from classpath and loads data from configuration file into ConfigFactory object
     */
    private static void initConfig() {
        try {
            if (config == null) {
                // getting class loader for specified class, it has to be precise since method is static
                ClassLoader classLoader = ConfigFactory.class.getClassLoader();
                // loading file from classpath, toURI() is needed when path contains special characters
                // requireNonNull method will point at this class when NullPointerEx is thrown
                File configFile = new File(Objects.requireNonNull(classLoader.getResource(CONFIG_FILE)).toURI());
//                File configFile = new File(CONFIG_FILE);
                Reader fileReader = new BufferedReader(new FileReader(configFile));
                config = new ConfigFactory();
                config.read(fileReader);
                config.setThrowExceptionOnMissing(true);
            }
        } catch (ConfigurationException | IOException | URISyntaxException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * Returns string value of given string key from the configuration file
     *
     * @param   param string key value
     * @return  The string value from configuration file, that corresponds with given key, if key is invalid
     *          method returns empty string
     *
     */
    public static String get(String param) {
        String configValue = "";
        try {
            initConfig();
            configValue = config.getString(param);
        } catch (NoSuchElementException ex) {
            log.error(ex.getMessage());
//            throw new EmptyMapperException("Empty mapper element");
        }
        return configValue;
    }

    public static SubnodeConfiguration getIniSection(String name) {
        initConfig();
        return config.getSection(name);
    }
}
