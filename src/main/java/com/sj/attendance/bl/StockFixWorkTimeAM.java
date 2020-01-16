package com.sj.attendance.bl;

// 固定工时-上午半天
public class StockFixWorkTimeAM extends FixWorkTimePolicy {
    StockFixWorkTimeAM() {
        super(StockWorkTime.FIX_AM,
                StockWorkTime.SHORT_TILE_AM,
                9 * TimeUtils.HOUR,
                5 * TimeUtils.HOUR);
    }
}
