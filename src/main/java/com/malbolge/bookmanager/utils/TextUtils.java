package com.malbolge.bookmanager.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TextUtils {

    private TextUtils() { /* cannot be instantiated */};

    public static boolean isEmpty(@Nullable final String str) {
        return str == null || str.length() == 0;
    }

    @Nonnull
    public static String nullToEmpty(@Nullable final String str) {
        return str == null ? "" : str;
    }
}
