package com.sj.attendance.bl;

import com.sj.time.DateTimeUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class WorkTimeCommonTest {
    static final long REAL_CHECK_IN_TIME_09_30 = DateTimeUtils.compoundTime(9, 30);

    static void testcase_FixWorkTime_Morning_Late(FixWorkTimePolicy policy) {
        // 08:30 没有迟到
        assertFalse(policy.isLate(DateTimeUtils.compoundTime(8, 30)));
        // 09:00 没有迟到
        assertFalse(policy.isLate(DateTimeUtils.compoundTime(9, 0)));
        // 09:01 已经迟到
        assertTrue(policy.isLate(DateTimeUtils.compoundTime(9, 1)));
    }

    static void testcase_FixWorkTime_FullDay_EarlyLeave(FixWorkTimePolicy policy) {
        // 17:59 早退
        assertTrue(policy.isEarlyLeave(DateTimeUtils.compoundTime(17, 59)));
        // 18:00 没有早退
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(18, 0)));
        // 18:01 没有早退
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(18, 1)));
    }

    static void testcase_FixWorkTime_PM_EarlyLeave(FixWorkTimePolicy policy) {
        // 13:30 早退
        assertTrue(policy.isEarlyLeave(DateTimeUtils.compoundTime(13, 30)));
        // 14:00 没有早退
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(14, 0)));
        // 14:10 没有早退
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(14, 10)));
    }

    static void testcase_FlexWorkTime_Morning_Late(FixWorkTimePolicy policy) {
        // 09:30 没有迟到
        assertFalse(policy.isLate(DateTimeUtils.compoundTime(9, 30)));
        // 10:00 没有迟到
        assertFalse(policy.isLate(DateTimeUtils.compoundTime(10, 0)));
        // 10:01 已经迟到
        assertTrue(policy.isLate(DateTimeUtils.compoundTime(10, 1)));
    }

    static void testcase_WorkTime_PM_Late(FixWorkTimePolicy policy) {
        // 13:59 没有迟到
        assertFalse(policy.isLate(DateTimeUtils.compoundTime(13, 59)));
        // 14:00 没有迟到
        assertFalse(policy.isLate(DateTimeUtils.compoundTime(14, 0)));
        // 14:01 迟到
        assertTrue(policy.isLate(DateTimeUtils.compoundTime(14, 1)));
    }

    static void testcase_FlexWorkTime_FullDay_EarlyLeave(FixWorkTimePolicy policy) {
        // 18:29 早退
        assertTrue(policy.isEarlyLeave(DateTimeUtils.compoundTime(18, 29)));
        // 18:30 没有早退
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(18, 30)));
        // 18:31 没有早退
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(18, 31)));
    }

    static void testcase_FlexWorkTime_AM_EarlyLeave(FixWorkTimePolicy policy) {
        // 14:29 早退
        assertTrue(policy.isEarlyLeave(DateTimeUtils.compoundTime(14, 29)));
        // 14:30 没有早退
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(14, 30)));
        // 14:31 没有早退
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(14, 31)));
    }
}
