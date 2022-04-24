package com.malbolge.bookmanager.utils;

/** Holds constants defined at compile time */
public final class BuildConstants {
    public static boolean DEBUG = true;

    static{
        if (!Boolean.parseBoolean(System.getenv("debug")))
            DEBUG = Boolean.parseBoolean(PropertiesReader.getProperty("debug"));
    }
}
