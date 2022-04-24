package com.malbolge.bookmanager.utils.logger;

import javax.annotation.Nonnull;

import static com.malbolge.bookmanager.utils.BuildConstants.DEBUG;
import static com.malbolge.bookmanager.utils.logger.LogDelegate.println_native;

public class Log {

    public static final char DEBUG_LEVEL = 0;
    public static final char WARN_LEVEL = 1;
    public static final char VERBOSE_LEVEL = 2;
    public static final char ERROR_LEVEL = 3;
    public static final char INFO_LEVEL = 4;

    private Log() { /* should not be instantiated */}

    public static class TerribleFailure extends Exception {
        TerribleFailure(String msg, Throwable cause) { super(msg, cause); }
    }

    /**
     * Send a {@link #DEBUG_LEVEL} log message.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     *            the class where the log call occurs.
     * @param message The message you would like logged.
     */
    public static int d(@Nonnull final String tag, @Nonnull final String message) {
        if (DEBUG)
            return println_native(DEBUG_LEVEL, tag, message);
        return 0;
    }

    /**
     * Send a {@link #INFO_LEVEL} log message.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     *            the class where the log call occurs.
     * @param message The message you would like logged.
     */
    public static int i(@Nonnull final String tag, @Nonnull final String message) {
        return println_native(INFO_LEVEL, tag, message);
    }

    /**
     * Send a {@link #WARN_LEVEL} log message.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     *            the class where the log call occurs.
     * @param message The message you would like logged.
     */
    public static int w(@Nonnull final String tag, @Nonnull final String message) {
        return println_native(WARN_LEVEL, tag, message);
    }

    /**
     * Send a {@link #ERROR_LEVEL} log message.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     *            the class where the log call occurs.
     * @param message The message you would like logged.
     */
    public static int e(@Nonnull final String tag, @Nonnull final String message) {
        return println_native(ERROR_LEVEL, tag, message);
    }

    /**
     * Send a {@link #VERBOSE_LEVEL} log message.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     *            the class where the log call occurs.
     * @param message The message you would like logged.
     */
    public static int v(@Nonnull final String tag, @Nonnull final String message) {
        return println_native(VERBOSE_LEVEL, tag, message);
    }
}
