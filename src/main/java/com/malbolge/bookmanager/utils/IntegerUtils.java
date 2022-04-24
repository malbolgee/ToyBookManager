package com.malbolge.bookmanager.utils;

public class IntegerUtils {

    public static int parseInt(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
