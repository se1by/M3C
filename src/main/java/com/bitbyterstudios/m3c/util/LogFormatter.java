package com.bitbyterstudios.m3c.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {


    @Override
    public String format(LogRecord logRecord) {
        return new StringBuilder()
                .append("[")
                .append(calcDate(logRecord.getMillis()))
                .append(" ")
                .append(logRecord.getLevel().getName())
                .append("] ")
                .append(logRecord.getMessage())
                .append("\n")
                .toString();
    }

    private String calcDate(long millis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(millis);
        return dateFormat.format(date);
    }
}
