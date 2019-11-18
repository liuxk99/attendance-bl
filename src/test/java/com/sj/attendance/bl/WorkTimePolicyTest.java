package com.sj.attendance.bl;

import org.junit.Test;

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
    public void testcase02_FixWorkTimeHalfDay_1st() throws Exception {
        FixWorkTimePolicy policy = new StockFixWorkTimeHalfDay_1st();
        System.out.println(policy);

        WorkTimeCommonTest.testcase_FixWorkTime_Morning_Late(policy);
        WorkTimeCommonTest.testcase_FixWorkTime_HalfDay_EarlyLeave(policy);
    }

    @Test
    public void testcase03_FixWorkTimeHalfDay_2nd() throws Exception {
        FixWorkTimePolicy policy = new StockFixWorkTimeHalfDay_2nd();
        System.out.println(policy);

        WorkTimeCommonTest.testcase_WorkTime_HalfDay_Late(policy);
        WorkTimeCommonTest.testcase_FixWorkTime_FullDay_EarlyLeave(policy);
    }

    @Test
    public void testcase04_FlexibleWorkTimeFullDay() throws Exception {
        FlexWorkTimePolicy flexibleWorkTime = new StockFlexWorkTimeFullDay();
        // 09:30 上班
        flexibleWorkTime.setRealCheckInTime(WorkTimeCommonTest.REAL_CHECK_IN_TIME_09_30);

        FixWorkTimePolicy policy = flexibleWorkTime;
        System.out.println(policy);

        WorkTimeCommonTest.testcase_FlexWorkTime_Morning_Late(policy);
        WorkTimeCommonTest.testcase_FlexWorkTime_FullDay_EarlyLeave(policy);
    }

    @Test
    public void testcase05_FlexibleWorkTimeHalfDay_1st() throws Exception {
        FlexWorkTimePolicy flexibleWorkTime = new StockFlexWorkTimeHalfDay_1st();
        // 09:30 上班
        flexibleWorkTime.setRealCheckInTime(WorkTimeCommonTest.REAL_CHECK_IN_TIME_09_30);

        FixWorkTimePolicy policy = flexibleWorkTime;
        System.out.println(policy);

        WorkTimeCommonTest.testcase_FlexWorkTime_Morning_Late(policy);
        WorkTimeCommonTest.testcase_FlexWorkTime_HalfDay_EarlyLeave(policy);
    }
}