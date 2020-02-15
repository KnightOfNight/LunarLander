/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

//import java.time.ZoneOffset;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * @author ctg
 */
public class LogFormatter extends Formatter {
    public final static Level LEVEL = Level.INFO;

    @Override
    public String format(LogRecord record) {
        String dt = ZonedDateTime.now( ZoneOffset.UTC ).format( DateTimeFormatter.ISO_INSTANT );
        String msg = String.format("%s [thread:%s] [%s.%s] [%s] %s\n", dt, record.getThreadID(), record.getSourceClassName(), record.getSourceMethodName(), record.getLevel(), record.getMessage());
        return(msg);
    }
}
