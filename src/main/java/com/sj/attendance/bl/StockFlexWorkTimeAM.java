package com.sj.attendance.bl;

// 弹性工时-上午半天
class StockFlexWorkTimeAM extends FlexWorkTimePolicy {
    StockFlexWorkTimeAM() {
        super(StockWorkTime.FLEX_AM,
                StockWorkTime.SHORT_TILE_AM,
                9 * TimeUtils.HOUR,
                5 * TimeUtils.HOUR,
                10 * TimeUtils.HOUR);
    }
}
