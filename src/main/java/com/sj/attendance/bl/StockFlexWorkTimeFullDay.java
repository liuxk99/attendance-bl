package com.sj.attendance.bl;

import com.sj.time.DateTimeUtils;

// 弹性工时-全天
class StockFlexWorkTimeFullDay extends FlexWorkTimePolicy {
    StockFlexWorkTimeFullDay() {
        super(StockWorkTime.FLEX_FULL_DAY,
                StockWorkTime.SHORT_TILE_FULL,
                9 * DateTimeUtils.HOUR,
                9 * DateTimeUtils.HOUR,
                10 * DateTimeUtils.HOUR);
    }
}
