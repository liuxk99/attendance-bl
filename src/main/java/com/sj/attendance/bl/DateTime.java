package com.sj.attendance.bl;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTime {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MILLISECONDS_PER_SECOND = 1000;

    public static final long SECOND = MILLISECONDS_PER_SECOND;
    public static final long MINUTE = SECOND * SECONDS_PER_MINUTE;
    public static final long HOUR = MINUTE * MINUTES_PER_HOUR;
    public static final long DAY = HOUR * HOURS_PER_DAY;

    static long compoundTime(long hour, long min) {
        return hour * HOUR + min * MINUTE;
    }

    static long compoundTime(long hour, long min, long sec) {
        return hour * HOUR + min * MINUTE + sec * SECOND;
    }

    static long[] getTime(long time) {
        long hour = time / HOUR;
        long min = (time - hour * HOUR) / MINUTE;
        return new long[]{hour, min};
    }

    public static String formatRefTime(long time) {
        long hour = time / HOUR;
        long min = (time - hour * HOUR) / MINUTE;
        return String.format(Locale.getDefault(), "%02d:%02d", hour, min);
    }

    public static String formatTime(Date date) {
        return String.format(Locale.getDefault(), "%02d:%02d", date.getHours(), date.getMinutes());
    }

    public static String formatTime(long time) {
        Date date = new Date();
        date.setTime(time);
        return String.format(Locale.getDefault(), "%02d:%02d", date.getHours(), date.getMinutes());
    }

    public static long compoundTime(long offset) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.clear();
        calendar.set(year, month, day);
        return calendar.getTimeInMillis() + offset;
    }

    static public long dayInMillisByDate(Date date) {
        Date tmp = (Date) date.clone();
        tmp.setHours(0);
        tmp.setMinutes(0);
        tmp.setSeconds(0);
        return tmp.getTime();
    }

    public static long timeInMillisByDate(Date date) {
        return date.getHours() * DateTime.HOUR + date.getMinutes() * DateTime.MINUTE + date.getSeconds() * DateTime.SECOND;
    }

    public static String formatTime(int hour, int minute) {
        return String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
    }
}
