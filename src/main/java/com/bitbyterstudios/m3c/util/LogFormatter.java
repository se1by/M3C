package com.bitbyterstudios.m3c.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {


    @Override
    public String format(LogRecord logRecord) {
        StringBuilder buff = new StringBuilder();
        buff.append("[");
        buff.append(calcDate(logRecord.getMillis()));
        buff.append(" ");
        buff.append(logRecord.getLevel().getName());
        buff.append("] ");
        buff.append(logRecord.getMessage());
        buff.append("\n");
        return buff.toString();
    }

    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("HH:mm:ss");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }
}
