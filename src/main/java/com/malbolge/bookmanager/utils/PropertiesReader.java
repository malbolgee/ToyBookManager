package com.malbolge.bookmanager.utils;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Reads the <i>.properties</i> file created from the <i>pom.xml</i> defined properties. */
public class PropertiesReader {

    private static final String sPropertiesFileName = "project.properties";
    private static final Properties mProperties = new Properties();

    static {
        try {
            InputStream is = PropertiesReader.class.getClassLoader().getResourceAsStream(sPropertiesFileName);
            mProperties.load(is);
        } catch (IOException e) {
            System.out.println("Could not read properties file: " + e.getMessage());
        }
    }

    private PropertiesReader() { /* should not be instantiated */}

    /**
     * Returns the property value associated with a property key.
     *
     * @param propertyName The property's name to retrieve the value for.
     */
    public static String getProperty(@Nonnull final String propertyName) {
        return mProperties.getProperty(propertyName);
    }
}
