package com.sj.attendance.bl;

public class StockFlexWorkTimeHalfDay_1st extends FlexWorkTimePolicy {
    StockFlexWorkTimeHalfDay_1st() {
        super(StockWorktime.FLEX_HALF_DAY_FIRST, 9 * DateTime.HOUR,
                5 * DateTime.HOUR, 10 * DateTime.HOUR);
    }
}
