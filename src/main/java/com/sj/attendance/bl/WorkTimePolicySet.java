package com.sj.attendance.bl;

import java.util.ArrayList;
import java.util.List;

public class WorkTimePolicySet {
    public String getTitle() {
        return title;
    }

    /*
     * XX集团-弹性工作制
     * */
    String title;

    public List<FixWorkTimePolicy> getWorkTimePolicyList() {
        return workTimePolicyList;
    }

    List<FixWorkTimePolicy> workTimePolicyList = new ArrayList<FixWorkTimePolicy>();

    public void addPolicy(FixWorkTimePolicy policy) {
        workTimePolicyList.add(policy);
    }

    public WorkTimePolicySet(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("title: %s\n", title));
        for (FixWorkTimePolicy policy : workTimePolicyList) {
            sb.append(policy.toShortString());
        }
        return sb.toString();
    }
}
