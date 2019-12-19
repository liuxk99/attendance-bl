package com.sj.attendance.bl;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testcase02_Time() throws ParseException {
        Date date1 = new Date();
        System.out.println(date1);

        String str = TimeUtils.toISO8601(date1);
        System.out.println(TimeUtils.toISO8601(date1));

        Date date2 = TimeUtils.fromISO8601(str);
        System.out.println(date2);

        assertEquals(date2, date1);
    }

    @Test
    public void testcase03_time() throws ParseException {
        String dateStr2 = "2012-05-31T13:48:04Z";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date d = sdf.parse(dateStr2);
        System.out.println(d);
    }

    @Test
    public void testcase04_time() {
        Date date = new Date();
        System.out.println(date);

        long dayTime = TimeUtils.getDayTime(date);
        long dayDate = TimeUtils.getDayDate(date);

        System.out.println(new Date(dayTime));
        System.out.println(new Date(dayDate));
    }
}