package com.sj.attendance.bl;

// 固定工作时间
class StockFixWorkTimeFullDay extends FixWorkTimePolicy {
    StockFixWorkTimeFullDay() {
        // 早09:00到晚06:00，中间9个小时
        super(StockWorktime.FIX_FULL_DAY, 9 * DateTime.HOUR, 9 * DateTime.HOUR);
    }
}
