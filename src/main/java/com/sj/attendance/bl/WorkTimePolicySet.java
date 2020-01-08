package com.sj.attendance.bl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkTimePolicySet {
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    private UUID uuid;

    public String getName() {
        return name;
    }

    /*
     * XX集团-弹性工作制
     * */
    private String name;

    public List<FixWorkTimePolicy> getWorkTimePolicyList() {
        return workTimePolicyList;
    }

    List<FixWorkTimePolicy> workTimePolicyList = new ArrayList<FixWorkTimePolicy>();

    public void addPolicy(FixWorkTimePolicy policy) {
        workTimePolicyList.add(policy);
    }

    public WorkTimePolicySet(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("title: %s\n", name));
        for (FixWorkTimePolicy policy : workTimePolicyList) {
            sb.append(policy.toShortString());
        }
        return sb.toString();
    }
}
