package com.sj.attendance.bl;

import com.sj.time.DateTimeUtils;

import org.junit.Test;

import java.util.Calendar;
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
    public void testcase_FlexibleWorkTimeFullDay_001() throws Exception {
        FlexWorkTimePolicy policy = new StockFlexWorkTimeFullDay();
        // 09:30 上班
        policy.setRealCheckInTime(WorkTimeCommonTest.REAL_CHECK_IN_TIME_09_30);

        System.out.println(policy);

        WorkTimeCommonTest.testcase_FlexWorkTime_Morning_Late(policy);
        WorkTimeCommonTest.testcase_FlexWorkTime_FullDay_EarlyLeave(policy);
    }

    @Test
    public void testcase_FlexibleWorkTimeFullDay_002() throws Exception {
        FlexWorkTimePolicy policy = new StockFlexWorkTimeFullDay();
        System.out.println(policy);

        Date now = new Date();
        // Late
        Date realCheckInDate = (Date) now.clone();
        {
            realCheckInDate.setHours(9);
            realCheckInDate.setMinutes(35);
            System.out.println("real check in date: " + realCheckInDate);

            long realCheckInTime = DateTimeUtils.getDayTime(realCheckInDate);
            System.out.println("real check in time: " + realCheckInTime);
        }

        assertFalse(policy.isLate(realCheckInDate));
        policy.setRealCheckInTime(realCheckInDate);
        System.out.println(policy);

        // Early leave
        Date planCheckOutDate;
        {
            long planCheckOutTime = policy.getPlanCheckOutTime(realCheckInDate);
            System.out.println("plan check out times: " + planCheckOutTime);

            planCheckOutDate = (Date) now.clone();
            planCheckOutDate.setTime(planCheckOutTime);
            System.out.println("plan check out date: " + planCheckOutDate);

            long checkOutTime = policy.getCheckOutTime();
            System.out.println("plan check out time: " + checkOutTime);
        }

        Date realCheckOutDate = (Date) now.clone();
        {
            realCheckOutDate.setHours(18);
            realCheckOutDate.setMinutes(40);
            System.out.println("real check out date: " + realCheckOutDate);

            long realCheckOutTime = DateTimeUtils.getDayTime(realCheckOutDate);
            System.out.println("real check out time: " + realCheckOutTime);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(realCheckOutDate);
            long realCheckOutTime2 = genTime(calendar);
            System.out.println("real check out time 2: " + realCheckOutTime2);
        }

        assertTrue(realCheckOutDate.after(planCheckOutDate));
        assertFalse(policy.isEarlyLeave(realCheckOutDate));
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
        flexibleWorkTime.setRealCheckInTime(DateTimeUtils.compoundTime(8, 55));

        FixWorkTimePolicy policy = flexibleWorkTime;
        System.out.println(policy);

        WorkTimeCommonTest.testcase_FlexWorkTime_Morning_Late(policy);

        // 13:56 早退
        assertTrue(policy.isEarlyLeave(DateTimeUtils.compoundTime(13, 56)));
        // 14:00 没有早退
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(14, 0)));
        // 14:01 没有早退
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(14, 1)));
    }

    @Test
    public void testcase08_FlexibleWorkTime_FullDay() throws Exception {
        FlexWorkTimePolicy flexibleWorkTime = new StockFlexWorkTimeFullDay();
        // 09:30 上班
        flexibleWorkTime.setRealCheckInTime(DateTimeUtils.compoundTime(9, 13));

        FixWorkTimePolicy policy = flexibleWorkTime;
        System.out.println(policy);

        WorkTimeCommonTest.testcase_FlexWorkTime_Morning_Late(policy);

        assertTrue(policy.isEarlyLeave(DateTimeUtils.compoundTime(18, 12)));
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(18, 15)));
        assertFalse(policy.isEarlyLeave(DateTimeUtils.compoundTime(18, 35)));
    }


    public static Calendar genDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar cal = (Calendar) calendar.clone();
        cal.clear();
        cal.set(year, month, day);
        return cal;
    }

    public static long genTime(Calendar calendar) {
        Calendar cal = genDate(calendar);
        return calendar.getTimeInMillis() - cal.getTimeInMillis();
    }

}