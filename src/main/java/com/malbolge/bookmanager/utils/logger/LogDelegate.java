package com.malbolge.bookmanager.utils.logger;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogDelegate {

    private static final DateTimeFormatter sDtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private LogDelegate() {}

    private static char priorityChar(int priority) {
        switch (priority) {
            case Log.VERBOSE_LEVEL:
                return 'V';
            case Log.DEBUG_LEVEL:
                return 'D';
            case Log.INFO_LEVEL:
                return 'I';
            case Log.ERROR_LEVEL:
                return 'E';
            case Log.WARN_LEVEL:
                return 'W';
            default:
                return '?';
        }
    }

    public static int println_native(int priority, @Nonnull String tag, @Nonnull String msgs) {
        int written = 0;
        final String prefix = append(priority, tag);
        for (String msg : msgs.split("\n")) {
            final String s = prefix + msg;
            System.out.println(s);
            written += s.length();
        }
        return written;
    }

    private static String append(int priority, String tag) {
        StringBuilder prefix = new StringBuilder();
        final String now = sDtf.format(LocalDateTime.now());
        prefix.append(now).append(" ").append(tag).append(" ").append(priorityChar(priority)).append(" ");
        return prefix.toString();
    }
}
