package com.example.lesson51homework.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateTimeFormatterUtil {
    public static final DateTimeFormatter DTF = new DateTimeFormatterBuilder()
            .appendPattern("yyyy")
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 1)
            .toFormatter();

    private DateTimeFormatterUtil() {
    }
}
