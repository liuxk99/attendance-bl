package com.sj.attendance.bl;

import com.sj.time.DateTimeUtils;

// 弹性工时-上午半天
class StockFlexWorkTimeAM extends FlexWorkTimePolicy {
    StockFlexWorkTimeAM() {
        super(StockWorkTime.FLEX_AM,
                StockWorkTime.SHORT_TILE_AM,
                9 * DateTimeUtils.HOUR,
                5 * DateTimeUtils.HOUR,
                10 * DateTimeUtils.HOUR);
    }
}
