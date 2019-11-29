package com.sj.attendance.bl;

import java.util.Date;

public class DateTime {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MILLISECONDS_PER_SECOND = 1000;

    public static final long SECOND = MILLISECONDS_PER_SECOND;
    public static final long MINUTE = SECOND * SECONDS_PER_MINUTE;
    public static final long HOUR = MINUTE * MINUTES_PER_HOUR;
    public static final long DAY = HOUR * HOURS_PER_DAY;

    static long calcTime(long hour, long min) {
        return hour * HOUR + min * MINUTE;
    }

    static long calcTime(long hour, long min, long sec) {
        return hour * HOUR + min * MINUTE + sec * SECOND;
    }

    static long[] getTime(long time) {
        long hour = time / HOUR;
        long min = (time - hour * HOUR) / MINUTE;
        return new long[]{hour, min};
    }

    public static String timeToString(long time) {
        long hour = time / HOUR;
        long min = (time - hour * HOUR) / MINUTE;
        return String.format("%02d:%02d", hour, min);
    }

    public static String formatTime(Date date) {
        return String.format("%02d:%02d", date.getHours(), date.getMinutes());
    }

    public static String formatTime(long time) {
        Date date = new Date();
        date.setTime(time);
        return String.format("%02d:%02d", date.getHours(), date.getMinutes());
    }

    static public long dayInMillisByDate(Date date) {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date.getTime();
    }

    public static long timeInMillisByDate(Date date) {
        long nowTime = date.getHours() * DateTime.HOUR + date.getMinutes() * DateTime.MINUTE + date.getSeconds() * DateTime.SECOND;
        return nowTime;
    }
}
