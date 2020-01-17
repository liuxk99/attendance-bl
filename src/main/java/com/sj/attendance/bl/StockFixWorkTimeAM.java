package com.sj.attendance.bl;

import com.sj.time.DateTimeUtils;

// 固定工时-上午半天
public class StockFixWorkTimeAM extends FixWorkTimePolicy {
    StockFixWorkTimeAM() {
        super(StockWorkTime.FIX_AM,
                StockWorkTime.SHORT_TILE_AM,
                9 * DateTimeUtils.HOUR,
                5 * DateTimeUtils.HOUR);
    }
}
