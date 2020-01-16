package com.sj.attendance.bl;

import com.fasterxml.uuid.Generators;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.sj.attendance.bl.TimeUtils.HOUR;

public class WorkTimePolicyFactory {
    static List<FixWorkTimePolicy> createStockPolicyList() {
        List<FixWorkTimePolicy> policies = new ArrayList<FixWorkTimePolicy>();
        policies.add(new StockFlexWorkTimeFullDay());
        policies.add(new StockFlexWorkTimeAM());
        policies.add(new StockFixWorkTimePM());
        policies.add(new StockFixWorkTimeFullDay());
        policies.add(new StockFixWorkTimeAM());
        return policies;
    }

    public static List<WorkTimePolicySet> createWorkTimePolicySetList() {
        List<WorkTimePolicySet> workTimePolicySetList = new ArrayList<>();
        workTimePolicySetList.add(createWorkTimePolicySetFixWorkTime());
        workTimePolicySetList.add(createWorkTimePolicySetFlexWorkTime());
        return workTimePolicySetList;
    }

    public static FixWorkTimePolicy createFixPolicyFD() {
        FixWorkTimePolicy policy = new StockFixWorkTimeFullDay();
        policy.setShortName(StockWorkTime.SHORT_TILE_FULL);
        return policy;
    }

    public static FixWorkTimePolicy createFixPolicyAM() {
        FixWorkTimePolicy policy = new StockFixWorkTimeAM();
        policy.setShortName(StockWorkTime.SHORT_TILE_AM);
        return policy;
    }

    public static FixWorkTimePolicy createFixPolicyPM() {
        FixWorkTimePolicy policy = new StockFixWorkTimePM();
        policy.setShortName(StockWorkTime.SHORT_TILE_PM);
        return policy;
    }

    static WorkTimePolicySet createWorkTimePolicySetFixWorkTime() {
        WorkTimePolicySet workTimePolicySet = new WorkTimePolicySet("XX集团-固定工时");
        {
            workTimePolicySet.addPolicy(createFixPolicyFD());
            workTimePolicySet.addPolicy(createFixPolicyAM());
            workTimePolicySet.addPolicy(createFixPolicyPM());
        }
        return workTimePolicySet;
    }

    static WorkTimePolicySet createWorkTimePolicySetFlexWorkTime() {
        WorkTimePolicySet workTimePolicySet = new WorkTimePolicySet("XX集团-弹性工时");
        {
            workTimePolicySet.addPolicy(createFlexPolicyFD());
            workTimePolicySet.addPolicy(createFlexPolicyAM());
            workTimePolicySet.addPolicy(createFixPolicyPM());
        }
        return workTimePolicySet;
    }

    public  static FixWorkTimePolicy createFlexPolicyFD() {
        FixWorkTimePolicy policy = new StockFlexWorkTimeFullDay();
        policy.setShortName(StockWorkTime.SHORT_TILE_FULL);
        return policy;
    }

    public  static FixWorkTimePolicy createFlexPolicyAM() {
        StockFlexWorkTimeAM policy = new StockFlexWorkTimeAM();
        policy.setShortName(StockWorkTime.SHORT_TILE_AM);
        return policy;
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
