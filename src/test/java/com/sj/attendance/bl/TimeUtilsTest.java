package com.sj.attendance.bl;

import org.junit.Test;

import java.util.Date;

public class TimeUtilsTest {

    @Test
    public void formatTime() {
        long checkInTime = 9 * TimeUtils.HOUR;
        System.out.println(TimeUtils.formatTime(checkInTime));

        long time = TimeUtils.compoundTime(checkInTime);
        Date date = new Date();
        date.setTime(time);
        System.out.println(date);
    }

}