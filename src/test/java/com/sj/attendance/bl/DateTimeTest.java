package com.sj.attendance.bl;

import org.junit.Test;

import java.util.Date;

public class DateTimeTest {

    @Test
    public void formatTime() {
        long checkInTime = 9 * DateTime.HOUR;
        System.out.println(DateTime.formatTime(checkInTime));

        long time = DateTime.compoundTime(checkInTime);
        Date date = new Date();
        date.setTime(time);
        System.out.println(date);
    }
}