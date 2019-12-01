package com.sj.attendance.bl;

import java.util.ArrayList;
import java.util.List;

public class WorkTimePolicyFactory {
    public static List<FixWorkTimePolicy> createPolicies() {
        List<FixWorkTimePolicy> policies = new ArrayList<FixWorkTimePolicy>();
        policies.add(new StockFlexWorkTimeFullDay());
        policies.add(new StockFlexWorkTimeHalfDay_1st());
        policies.add(new StockFixWorkTimeHalfDay_2nd());
        policies.add(new StockFixWorkTimeFullDay());
        policies.add(new StockFixWorkTimeHalfDay_1st());
        return policies;
    }
}
