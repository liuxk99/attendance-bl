package com.sj.attendance.bl;

// 固定工时-全天
public class StockFixWorkTimeFullDay extends FixWorkTimePolicy {
    public StockFixWorkTimeFullDay() {
        // 早09:00到晚06:00，中间9个小时
        super(StockWorktime.FIX_FULL_DAY, 9 * TimeUtils.HOUR, 9 * TimeUtils.HOUR);
    }
}
