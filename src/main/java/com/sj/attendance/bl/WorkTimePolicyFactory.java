package com.sj.attendance.bl;

import java.util.ArrayList;
import java.util.List;

public class WorkTimePolicyFactory {
    static List<FixWorkTimePolicy> createPolicies() {
        List<FixWorkTimePolicy> policies = new ArrayList<FixWorkTimePolicy>();
        policies.add(new StockFlexWorkTimeFullDay());
        policies.add(new StockFlexWorkTimeAM());
        policies.add(new StockFixWorkTimePM());
        policies.add(new StockFixWorkTimeFullDay());
        policies.add(new StockFixWorkTimeAM());
        return policies;
    }

    public static List<WorkTimePolicySet> createWorkTimePolicySetList() {
        List<WorkTimePolicySet> workTimePolicySetList = new ArrayList<WorkTimePolicySet>();
        workTimePolicySetList.add(createWorkTimePolicySetFixWorkTime());
        workTimePolicySetList.add(createWorkTimePolicySetFlexWorkTime());
        return workTimePolicySetList;
    }

    static WorkTimePolicySet createWorkTimePolicySetFixWorkTime() {
        WorkTimePolicySet workTimePolicySet = new WorkTimePolicySet("XX集团-固定工时");
        FixWorkTimePolicy policy = new StockFixWorkTimeFullDay();
        policy.setShortName(StockWorktime.SHORT_TILE_FULL);
        workTimePolicySet.addPolicy(policy);

        policy = new StockFixWorkTimeAM();
        policy.setShortName(StockWorktime.SHORT_TILE_AM);
        workTimePolicySet.addPolicy(policy);

        policy = new StockFixWorkTimePM();
        policy.setShortName(StockWorktime.SHORT_TILE_PM);
        workTimePolicySet.addPolicy(policy);

        return workTimePolicySet;
    }

    static WorkTimePolicySet createWorkTimePolicySetFlexWorkTime() {
        WorkTimePolicySet workTimePolicySet = new WorkTimePolicySet("XX集团-弹性工时");
        FixWorkTimePolicy policy = new StockFlexWorkTimeFullDay();
        policy.setShortName(StockWorktime.SHORT_TILE_FULL);
        workTimePolicySet.addPolicy(policy);

        policy = new StockFlexWorkTimeAM();
        policy.setShortName(StockWorktime.SHORT_TILE_AM);
        workTimePolicySet.addPolicy(policy);

        policy = new StockFixWorkTimePM();
        policy.setShortName(StockWorktime.SHORT_TILE_PM);
        workTimePolicySet.addPolicy(policy);

        return workTimePolicySet;
    }
}
