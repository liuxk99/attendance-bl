package com.sj.attendance.bl;

import org.junit.Test;

import java.util.List;

import static com.sj.attendance.bl.WorkTimePolicyFactory.createWorkTimePolicySetFixWorkTime;
import static com.sj.attendance.bl.WorkTimePolicyFactory.createWorkTimePolicySetFlexWorkTime;
import static com.sj.attendance.bl.WorkTimePolicyFactory.createWorkTimePolicySetList;

public class WorkTimePolicyTestSuite {

    @Test
    public void testcase_FixWorkTimePolicySet() {
        WorkTimePolicySet workTimePolicySet = createWorkTimePolicySetFixWorkTime();
        System.out.println(workTimePolicySet.toString());
    }

    @Test
    public void testcase_FlexWorkTimePolicySet() {
        WorkTimePolicySet workTimePolicySet = createWorkTimePolicySetFlexWorkTime();
        System.out.println(workTimePolicySet.toString());
    }

    @Test
    public void testcase_PolicySet() {
        List<WorkTimePolicySet> workTimePolicySetList = createWorkTimePolicySetList();
        for (WorkTimePolicySet policySet : workTimePolicySetList) {
            System.out.println("=>---");
            System.out.print(policySet);
            System.out.println("<----");
        }
    }
}