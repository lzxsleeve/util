package com.sleeve.util;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间相关的工具类
 * <p>
 * Create by liuzx on date 2019/7/12.
 */
public class LDateUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_MILLISECOND_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 时间戳转日期
     */
    public static String getDateTime(long millisecond) {
        return getDate(millisecond, null, DATE_TIME_PATTERN);
    }

    /**
     * 时间戳转日期
     */
    public static String getDate(long millisecond, @NonNull String pattern) {
        return getDate(millisecond, null, pattern);
    }

    /**
     * 时间戳转日期
     *
     * @param timeZone 时区 (UTC+8:00 或 Asia/Shanghai)
     */
    public static String getDate(long millisecond, String timeZone, @NonNull String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
            if (timeZone != null) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
            }
            return dateFormat.format(millisecond);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 时间字符串转Date
     */
    public static Date getDate(String time) {
        return getDate(time, DATE_TIME_PATTERN);
    }

    /**
     * 时间字符串转Date
     */
    public static Date getDate(String time, @NonNull String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期转时间戳
     */
    public static long getMillisecond(String dateTime) {
        return getMillisecond(dateTime, null, DATE_TIME_PATTERN);
    }

    /**
     * 日期转时间戳
     */
    public static long getMillisecond(String date, @NonNull String pattern) {
        return getMillisecond(date, null, pattern);
    }

    /**
     * 日期转时间戳
     *
     * @param timeZone 时区 (UTC+8:00 或 Asia/Shanghai)
     */
    public static long getMillisecond(String date, String timeZone, @NonNull String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
            if (timeZone != null) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
            }
            return dateFormat.parse(date).getTime();
        } catch (Exception e) {
            return 0;
        }
    }
}
