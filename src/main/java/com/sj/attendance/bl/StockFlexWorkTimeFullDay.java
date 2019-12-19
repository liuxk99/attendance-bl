package com.sj.attendance.bl;

// 弹性工时-全天
public class StockFlexWorkTimeFullDay extends FlexWorkTimePolicy {
    StockFlexWorkTimeFullDay() {
        super(StockWorktime.FLEX_FULL_DAY, 9 * TimeUtils.HOUR,
                9 * TimeUtils.HOUR, 10 * TimeUtils.HOUR);
    }
}
