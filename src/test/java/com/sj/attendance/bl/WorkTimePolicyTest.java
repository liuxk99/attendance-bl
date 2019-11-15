package com.sj.attendance.bl;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WorkTimePolicyTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFixWorkTime() throws Exception {
        FixWorkTime fixWorkTime = new FixWorkTime();

        // 08:30 没有迟到
        assertFalse(fixWorkTime.isLate(getTime(8.5f)));
        // 09:00 没有迟到
        assertFalse(fixWorkTime.isLate(getTime(9L)));
        // 10:00 已经迟到
        assertTrue(fixWorkTime.isLate(getTime(10.0f)));

        // 17:30 早退
        assertTrue(fixWorkTime.isEarlyLeave(getTime(17.5f)));
        // 18:00 没有早退
        assertFalse(fixWorkTime.isEarlyLeave(getTime(18L)));
        // 18:30 没有早退
        assertFalse(fixWorkTime.isEarlyLeave(getTime(18.5f)));
    }

    private long getTime(float hour) {
        return (long) (hour * DateTime.HOUR);
    }

    private long getTime(long hour) {
        return hour * DateTime.HOUR;
    }

    @Test
    public void testFlexibleWorkTime() throws Exception {
        FlexibleWorkTime fixWorkTime = new FlexibleWorkTime(9 * DateTime.HOUR,
                9 * DateTime.HOUR, 10 * DateTime.HOUR);

        // 09:30 上班
        fixWorkTime.setRealCheckInTime(9L * DateTime.HOUR + 30L * DateTime.MINUTE);

        // 08:30 没有迟到
        assertFalse(fixWorkTime.isLate(getTime(8.5f)));
        // 09:30 没有迟到
        assertFalse(fixWorkTime.isLate(getTime(9.5f)));
        // 09:30 没有迟到
        assertFalse(fixWorkTime.isLate(getTime(10L)));
        // 10:30 已经迟到
        assertTrue(fixWorkTime.isLate(getTime(10.5f)));

        // 17:30 早退
        assertTrue(fixWorkTime.isEarlyLeave(getTime(17.5f)));
        // 18:00 早退
        assertTrue(fixWorkTime.isEarlyLeave(getTime(18L)));
        // 18:30 没有早退
        assertFalse(fixWorkTime.isEarlyLeave(18L * DateTime.HOUR + 30L * DateTime.MINUTE));
        // 18:30 没有早退
        assertFalse(fixWorkTime.isEarlyLeave(getTime(19.0f)));
    }

    @Test
    public void testFixWorkTimeAfternoon() throws Exception{
        FixWorkTimeAfternoon afternoon = new FixWorkTimeAfternoon();

        // 08:30 没有迟到
        assertFalse(afternoon.isLate(getTime(8.5f)));
        // 14:00 没有迟到
        assertFalse(afternoon.isLate(getTime(14L)));
        // 14:30 没有迟到
        assertTrue(afternoon.isLate(getTime(14.5f)));

        // 17:30 早退
        assertTrue(afternoon.isEarlyLeave(getTime(17.5f)));
        // 18:00 没有早退
        assertFalse(afternoon.isEarlyLeave(getTime(18L)));
        // 18:30 没有早退
        assertFalse(afternoon.isEarlyLeave(18L * DateTime.HOUR + 30L * DateTime.MINUTE));
        // 18:30 没有早退
        assertFalse(afternoon.isEarlyLeave(getTime(19.0f)));
    }
}