package com.gameofthree.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;


public class PropertiesConfig {
	   /**
     * Stores application properties
     */
    private static Properties properties = new Properties();

    public static Properties getProperties() {
        return properties;
    }

    private PropertiesConfig() {
    }


    public static void initialize(String filename) {
        loadProperties(filename);
    }

    private static void loadProperties(String filename) {

        try(InputStream input = PropertiesConfig.class.getClassLoader().getResourceAsStream(filename)) {
            if (Objects.isNull(input)) throw new FileNotFoundException();
            properties.load(input);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File: '" + filename + "' not found.");
        } catch(IOException er) {
            throw new RuntimeException("IO error while reading properties file.");
        }
    }
}
