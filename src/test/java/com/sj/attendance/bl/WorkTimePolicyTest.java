package com.sj.attendance.bl;

import org.junit.Test;

import java.util.Date;

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
    public void testcase01_FixWorkTimeFullDay() throws Exception {
        FixWorkTimePolicy policy = new StockFixWorkTimeFullDay();
        System.out.println(policy);

        WorkTimeCommonTest.testcase_FixWorkTime_Morning_Late(policy);
        WorkTimeCommonTest.testcase_FixWorkTime_FullDay_EarlyLeave(policy);
    }

    @Test
    public void testcase02_FixWorkTimeFullDay() throws Exception {
        FixWorkTimePolicy policy = new StockFixWorkTimeFullDay();
        System.out.println(policy);

        Date now = new Date();
//        System.out.println(now.getHours());
//        System.out.println(now.getMinutes());
//        System.out.println(now.getSeconds());

        long time = TimeUtils.compoundTime(now.getHours(), now.getMinutes(), now.getSeconds());
        assertTrue(policy.isLate(time));
        assertTrue(policy.isEarlyLeave(time));
    }

    @Test
    public void testcase03_FixWorkTime_AM() throws Exception {
        FixWorkTimePolicy policy = new StockFixWorkTimeAM();
        System.out.println(policy);

        WorkTimeCommonTest.testcase_FixWorkTime_Morning_Late(policy);
        WorkTimeCommonTest.testcase_FixWorkTime_PM_EarlyLeave(policy);
    }

    @Test
    public void testcase04_FixWorkTime_PM() throws Exception {
        FixWorkTimePolicy policy = new StockFixWorkTimePM();
        System.out.println(policy);

        WorkTimeCommonTest.testcase_WorkTime_PM_Late(policy);
        WorkTimeCommonTest.testcase_FixWorkTime_FullDay_EarlyLeave(policy);
    }

    @Test
    public void testcase05_FlexibleWorkTimeFullDay() throws Exception {
        FlexWorkTimePolicy flexibleWorkTime = new StockFlexWorkTimeFullDay();
        // 09:30 上班
        flexibleWorkTime.setRealCheckInTime(WorkTimeCommonTest.REAL_CHECK_IN_TIME_09_30);

        FixWorkTimePolicy policy = flexibleWorkTime;
        System.out.println(policy);

        WorkTimeCommonTest.testcase_FlexWorkTime_Morning_Late(policy);
        WorkTimeCommonTest.testcase_FlexWorkTime_FullDay_EarlyLeave(policy);
    }

    @Test
    public void testcase06_FlexibleWorkTime_AM() throws Exception {
        FlexWorkTimePolicy flexibleWorkTime = new StockFlexWorkTimeAM();
        // 09:30 上班
        flexibleWorkTime.setRealCheckInTime(WorkTimeCommonTest.REAL_CHECK_IN_TIME_09_30);

        FixWorkTimePolicy policy = flexibleWorkTime;
        System.out.println(policy);

        WorkTimeCommonTest.testcase_FlexWorkTime_Morning_Late(policy);
        WorkTimeCommonTest.testcase_FlexWorkTime_AM_EarlyLeave(policy);
    }

    @Test
    public void testcase07_FlexibleWorkTime_AM() throws Exception {
        FlexWorkTimePolicy flexibleWorkTime = new StockFlexWorkTimeAM();
        // 09:30 上班
        flexibleWorkTime.setRealCheckInTime(TimeUtils.compoundTime(8, 55));

        FixWorkTimePolicy policy = flexibleWorkTime;
        System.out.println(policy);

        WorkTimeCommonTest.testcase_FlexWorkTime_Morning_Late(policy);

        // 13:56 早退
        assertTrue(policy.isEarlyLeave(TimeUtils.compoundTime(13, 56)));
        // 14:00 没有早退
        assertFalse(policy.isEarlyLeave(TimeUtils.compoundTime(14, 0)));
        // 14:01 没有早退
        assertFalse(policy.isEarlyLeave(TimeUtils.compoundTime(14, 1)));
    }

}