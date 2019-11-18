package com.sj.attendance.bl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class WorkTimeCommonTest {
    static final long REAL_CHECK_IN_TIME_09_30 = DateTime.calcTime(9, 30);

    static void testcase_FixWorkTime_Morning_Late(FixWorkTimePolicy policy) {
        // 08:30 没有迟到
        assertFalse(policy.isLate(DateTime.calcTime(8, 30)));
        // 09:00 没有迟到
        assertFalse(policy.isLate(DateTime.calcTime(9, 0)));
        // 09:01 已经迟到
        assertTrue(policy.isLate(DateTime.calcTime(9, 1)));
    }

    static void testcase_FixWorkTime_FullDay_EarlyLeave(FixWorkTimePolicy policy) {
        // 17:59 早退
        assertTrue(policy.isEarlyLeave(DateTime.calcTime(17, 59)));
        // 18:00 没有早退
        assertFalse(policy.isEarlyLeave(DateTime.calcTime(18, 0)));
        // 18:01 没有早退
        assertFalse(policy.isEarlyLeave(DateTime.calcTime(18, 1)));
    }

    static void testcase_FixWorkTime_HalfDay_EarlyLeave(FixWorkTimePolicy policy) {
        // 13:30 早退
        assertTrue(policy.isEarlyLeave(DateTime.calcTime(13, 30)));
        // 14:00 没有早退
        assertFalse(policy.isEarlyLeave(DateTime.calcTime(14, 0)));
        // 14:10 没有早退
        assertFalse(policy.isEarlyLeave(DateTime.calcTime(14, 10)));
    }

    static void testcase_FlexWorkTime_Morning_Late(FixWorkTimePolicy policy) {
        // 09:30 没有迟到
        assertFalse(policy.isLate(DateTime.calcTime(9, 30)));
        // 10:00 没有迟到
        assertFalse(policy.isLate(DateTime.calcTime(10, 0)));
        // 10:01 已经迟到
        assertTrue(policy.isLate(DateTime.calcTime(10, 1)));
    }

    static void testcase_WorkTime_HalfDay_Late(FixWorkTimePolicy policy) {
        // 13:59 没有迟到
        assertFalse(policy.isLate(DateTime.calcTime(13, 59)));
        // 14:00 没有迟到
        assertFalse(policy.isLate(DateTime.calcTime(14, 0)));
        // 14:01 迟到
        assertTrue(policy.isLate(DateTime.calcTime(14, 1)));
    }

    static void testcase_FlexWorkTime_FullDay_EarlyLeave(FixWorkTimePolicy policy) {
        // 18:29 早退
        assertTrue(policy.isEarlyLeave(DateTime.calcTime(18, 29)));
        // 18:30 没有早退
        assertFalse(policy.isEarlyLeave(DateTime.calcTime(18, 30)));
        // 18:31 没有早退
        assertFalse(policy.isEarlyLeave(DateTime.calcTime(18, 31)));
    }

    static void testcase_FlexWorkTime_HalfDay_EarlyLeave(FixWorkTimePolicy policy) {
        // 14:29 早退
        assertTrue(policy.isEarlyLeave(DateTime.calcTime(14, 29)));
        // 14:30 没有早退
        assertFalse(policy.isEarlyLeave(DateTime.calcTime(14, 30)));
        // 14:31 没有早退
        assertFalse(policy.isEarlyLeave(DateTime.calcTime(14, 31)));
    }
}
