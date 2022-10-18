package com.kemido.assistant.core.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>Description: 特殊日期处理 </p>
 */
public class DateTimeUtils {

    private static final String DEFAULT_DATA_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_TIME_ZONE_NAME = "Asia/Shanghai";

    public static String zonedDateTimeToString(ZonedDateTime zonedDateTime) {
        return zonedDateTimeToString(zonedDateTime, DEFAULT_DATA_TIME_FORMAT);
    }

    public static String zonedDateTimeToString(ZonedDateTime zonedDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.of(DEFAULT_TIME_ZONE_NAME));
        return zonedDateTime.format(formatter);
    }

    public static ZonedDateTime stringToZonedDateTime(String dateString){
        return stringToZonedDateTime(dateString, DEFAULT_DATA_TIME_FORMAT);
    }

    public static ZonedDateTime stringToZonedDateTime(String dateString, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.of(DEFAULT_TIME_ZONE_NAME));
        return ZonedDateTime.parse(dateString, formatter);
    }
}
