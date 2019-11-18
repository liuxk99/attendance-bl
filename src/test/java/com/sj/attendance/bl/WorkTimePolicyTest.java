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

    private long getTime(long hour, long min){
        return hour * DateTime.HOUR + min * DateTime.MINUTE;
    }

    @Test
    public void testcase01_FixWorkTimeFullDay() throws Exception {
        FixWorkTimePolicy policy = new StockFixWorkTimeFullDay();
        System.out.println(policy.name);

        // 08:30 没有迟到
        assertFalse(policy.isLate(getTime(8, 30)));
        // 09:00 没有迟到
        assertFalse(policy.isLate(getTime(9, 0)));
        // 09:01 已经迟到
        assertTrue(policy.isLate(getTime(9, 1)));

        // 17:59 早退
        assertTrue(policy.isEarlyLeave(getTime(17, 59)));
        // 18:00 没有早退
        assertFalse(policy.isEarlyLeave(getTime(18, 0)));
        // 18:01 没有早退
        assertFalse(policy.isEarlyLeave(getTime(18, 1)));
    }

    @Test
    public void testcase02_FixWorkTimeHalfDay_1st() throws Exception {
        FixWorkTimePolicy policy = new StockFixWorkTimeHalfDay_1st();
        System.out.println(policy.name);

        // 08:30 没有迟到
        assertFalse(policy.isLate(getTime(8, 30)));
        // 09:00 没有迟到
        assertFalse(policy.isLate(getTime(9, 0)));
        // 09:01 已经迟到
        assertTrue(policy.isLate(getTime(9, 1)));

        // 13:30 早退
        assertTrue(policy.isEarlyLeave(getTime(13, 30)));
        // 14:00 没有早退
        assertFalse(policy.isEarlyLeave(getTime(14, 0)));
        // 14:10 没有早退
        assertFalse(policy.isEarlyLeave(getTime(14, 10)));
    }

    @Test
    public void testcase03_FixWorkTimeHalfDay_2nd() throws Exception {
        FixWorkTimePolicy policy = new StockFixWorkTimeHalfDay_2nd();
        System.out.println(policy.name);

        // 13:59 没有迟到
        assertFalse(policy.isLate(getTime(13, 59)));
        // 14:00 没有迟到
        assertFalse(policy.isLate(getTime(14, 0)));
        // 14:01 迟到
        assertTrue(policy.isLate(getTime(14, 1)));

        // 17:59 早退
        assertTrue(policy.isEarlyLeave(getTime(17, 59)));
        // 18:00 没有早退
        assertFalse(policy.isEarlyLeave(getTime(18, 0)));
        // 18:01 没有早退
        assertFalse(policy.isEarlyLeave(getTime(18, 1)));
    }

    @Test
    public void testcase04_FlexibleWorkTimeFullDay() throws Exception {
        FlexWorkTimePolicy flexibleWorkTime = new StockFlexWorkTimeFullDay();
        // 09:30 上班
        flexibleWorkTime.setRealCheckInTime(9L * DateTime.HOUR + 30L * DateTime.MINUTE);

        FixWorkTimePolicy policy = flexibleWorkTime;
        System.out.println(policy.name);


        // 08:30 没有迟到
        assertFalse(policy.isLate(getTime(8, 30)));
        // 09:30 没有迟到
        assertFalse(policy.isLate(getTime(9, 30)));
        // 10:00 没有迟到
        assertFalse(policy.isLate(getTime(10, 0)));
        // 10:01 已经迟到
        assertTrue(policy.isLate(getTime(10, 1)));

        // 18:00 早退
        assertTrue(policy.isEarlyLeave(getTime(18, 0)));
        // 18:30 没有早退
        assertFalse(policy.isEarlyLeave(getTime(18, 30)));
        // 18:31 没有早退
        assertFalse(policy.isEarlyLeave(getTime(18, 31)));
    }

    @Test
    public void testcase05_FlexibleWorkTimeHalfDay_1st() throws Exception {
        FlexWorkTimePolicy flexibleWorkTime = new StockFlexWorkTimeHalfDay_1st();
        // 09:30 上班
        flexibleWorkTime.setRealCheckInTime(getTime(9, 30));

        FixWorkTimePolicy policy = flexibleWorkTime;
        System.out.println(policy.name);

        // 08:30 没有迟到
        assertFalse(policy.isLate(getTime(8, 30)));
        // 09:30 没有迟到
        assertFalse(policy.isLate(getTime(9, 30)));
        // 10:00 没有迟到
        assertFalse(policy.isLate(getTime(10, 0)));
        // 10:01 已经迟到
        assertTrue(policy.isLate(getTime(10, 1)));

        // 14:29 早退
        assertTrue(policy.isEarlyLeave(getTime(14, 29)));
        // 14:30 没有早退
        assertFalse(policy.isEarlyLeave(getTime(14, 30)));
        // 14:31 没有早退
        assertFalse(policy.isEarlyLeave(getTime(14, 31)));
    }
}