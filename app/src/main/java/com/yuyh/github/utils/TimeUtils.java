package com.yuyh.github.utils;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.text.format.DateUtils.FORMAT_NUMERIC_DATE;
import static android.text.format.DateUtils.FORMAT_SHOW_DATE;
import static android.text.format.DateUtils.FORMAT_SHOW_YEAR;
import static android.text.format.DateUtils.MINUTE_IN_MILLIS;

public class TimeUtils {

    /**
     * Get relative time for date.
     *
     * @param date
     * @return relative time.
     */
    public static CharSequence getRelativeTime(final Date date) {
        long now = System.currentTimeMillis();
        if (Math.abs(now - date.getTime()) > 60000)
            return DateUtils.getRelativeTimeSpanString(date.getTime(), now,
                    MINUTE_IN_MILLIS, FORMAT_SHOW_DATE | FORMAT_SHOW_YEAR
                            | FORMAT_NUMERIC_DATE);
        else
            return "just now";
    }

    public static CharSequence getRelativeTime(final String date) {
        return getRelativeTime(stringToDate(date));
    }

    /**
     * Convert string datetime.
     *
     * @param value The datetime.
     * @return Local datetime.
     */
    public static Date stringToDate(String value) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            return format.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateToString(Date value) {
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return formats.format(value);
    }
}