package com.sj.attendance.bl;

// 弹性工时-上午半天
public class StockFlexWorkTimeAM extends FlexWorkTimePolicy {
    StockFlexWorkTimeAM() {
        super(StockWorktime.FLEX_HALF_DAY_FIRST, 9 * TimeUtils.HOUR,
                5 * TimeUtils.HOUR, 10 * TimeUtils.HOUR);
    }
}
