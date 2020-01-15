package com.sj.attendance.bl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

    public List<FixWorkTimePolicy> getPolicyList() {
        return policyList;
    }

    private List<FixWorkTimePolicy> policyList = new ArrayList<FixWorkTimePolicy>();

    public void addPolicy(FixWorkTimePolicy policy) {
        policyList.add(policy);
    }

    public WorkTimePolicySet(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("title: %s\n", name));
        for (FixWorkTimePolicy policy : policyList) {
            sb.append(policy.toShortString());
        }
        sb.append(String.format(Locale.getDefault(), "index: %d\n", index));
        return sb.toString();
    }

    //{{ index
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        if (index != this.index) {
            this.index = index;
            policy = policyList.get(index);
        }
    }

    private int index = 0;
    //}}

    //{{ work-time policy
    public FixWorkTimePolicy getPolicy() {
        return policy;
    }

    public void setPolicy(FixWorkTimePolicy policy) {
        if (policy != this.policy) {
            this.policy = policy;
            for (int i = 0; i < policyList.size(); i++) {
                if (policy == policyList.get(i)) {
                    index = i;
                    break;
                }
            }
        }
    }

    private FixWorkTimePolicy policy;
    //}}
}
