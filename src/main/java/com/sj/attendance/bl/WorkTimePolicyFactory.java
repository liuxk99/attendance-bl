package com.sj.attendance.bl;

import com.fasterxml.uuid.Generators;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.sj.attendance.bl.TimeUtils.HOUR;

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

    public static FixWorkTimePolicy generateFlexPolicy() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        FlexWorkTimePolicy policy = new FlexWorkTimePolicy("xx集团-固定工时-全天",
                "全天",
                TimeUtils.DEF_CHECK_IN_HOUR * HOUR,
                (TimeUtils.DEF_CHECK_OUT_HOUR - TimeUtils.DEF_CHECK_IN_HOUR) * HOUR,
                TimeUtils.DEF_LATEST_CHECK_IN_HOUR * HOUR
        );
        policy.setUuid(uuid);
        return policy;
    }
}
