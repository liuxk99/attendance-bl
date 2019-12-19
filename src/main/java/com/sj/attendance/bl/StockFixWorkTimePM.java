package com.sj.attendance.bl;

// 固定工时-下午半天
class StockFixWorkTimePM extends FixWorkTimePolicy {

    StockFixWorkTimePM() {
        // 14:00到18:00，中间4个小时
        super(StockWorktime.HALF_DAY_SECOND, 14 * TimeUtils.HOUR, 4 * TimeUtils.HOUR);
    }
}
