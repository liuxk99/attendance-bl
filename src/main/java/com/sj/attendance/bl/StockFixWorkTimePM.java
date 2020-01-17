package com.sj.attendance.bl;

import com.sj.time.DateTimeUtils;

// 固定工时-下午半天
class StockFixWorkTimePM extends FixWorkTimePolicy {

    StockFixWorkTimePM() {
        // 14:00到18:00，中间4个小时
        super(StockWorkTime.ALL_PM,
                StockWorkTime.SHORT_TILE_PM,
                14 * DateTimeUtils.HOUR,
                4 * DateTimeUtils.HOUR);
    }
}
