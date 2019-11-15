package com.sj.attendance.bl;

public class DateTime {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MILLISECONDS_PER_SECOND = 1000;

    private static final long SECOND = MILLISECONDS_PER_SECOND;
    static final long MINUTE = SECOND * SECONDS_PER_MINUTE;
    static final long HOUR = MINUTE * MINUTES_PER_HOUR;
    public static final long DAY = HOUR * HOURS_PER_DAY;
}
