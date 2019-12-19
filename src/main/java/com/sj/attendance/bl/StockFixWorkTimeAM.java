package com.sj.attendance.bl;

// 固定工时-上午半天
public class StockFixWorkTimeAM extends FixWorkTimePolicy {
    StockFixWorkTimeAM() {
        super(StockWorktime.FIX_HALF_DAY_FIRST, 9 * TimeUtils.HOUR, 5 * TimeUtils.HOUR);
    }
}
