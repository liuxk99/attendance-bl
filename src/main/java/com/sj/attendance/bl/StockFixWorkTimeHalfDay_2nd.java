package com.sj.attendance.bl;

// 固定工作时间下午半天
class StockFixWorkTimeHalfDay_2nd extends FixWorkTimePolicy {

    StockFixWorkTimeHalfDay_2nd() {
        // 14:00到18:00，中间4个小时
        super(StockWorktime.HALF_DAY_SECOND, 14 * DateTime.HOUR, 4 * DateTime.HOUR);
    }
}
