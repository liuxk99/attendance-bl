package com.sj.attendance.bl;

// 固定工时-上午半天
public class StockFixWorkTimeHalfDay_1st extends FixWorkTimePolicy {
    StockFixWorkTimeHalfDay_1st() {
        super(StockWorktime.FIX_HALF_DAY_FIRST, 9 * DateTime.HOUR, 5 * DateTime.HOUR);
    }
}
