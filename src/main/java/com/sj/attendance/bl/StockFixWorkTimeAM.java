package com.sj.attendance.bl;

// 固定工时-上午半天
public class StockFixWorkTimeAM extends FixWorkTimePolicy {
    StockFixWorkTimeAM() {
        super(StockWorktime.FIX_HALF_DAY_FIRST, 9 * DateTime.HOUR, 5 * DateTime.HOUR);
    }
}
