package com.sj.attendance.bl;

// 弹性工时-全天
class StockFlexWorkTimeFullDay extends FlexWorkTimePolicy {
    StockFlexWorkTimeFullDay() {
        super(StockWorkTime.FLEX_FULL_DAY,
                StockWorkTime.SHORT_TILE_FULL,
                9 * TimeUtils.HOUR,
                9 * TimeUtils.HOUR,
                10 * TimeUtils.HOUR);
    }
}
