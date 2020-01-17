package com.sj.attendance.bl;

import com.sj.time.DateTimeUtils;

// 固定工时-全天
public class StockFixWorkTimeFullDay extends FixWorkTimePolicy {
    public StockFixWorkTimeFullDay() {
        // 早09:00到晚06:00，中间9个小时
        super(StockWorkTime.FIX_FULL_DAY,
                StockWorkTime.SHORT_TILE_FULL,
                9 * DateTimeUtils.HOUR,
                9 * DateTimeUtils.HOUR);
    }
}
