package com.sj.attendance.bl;

public class StockFlexWorkTimeFullDay extends FlexWorkTimePolicy {
    StockFlexWorkTimeFullDay() {
        super(StockWorktime.FLEX_FULL_DAY, 9 * DateTime.HOUR,
                9 * DateTime.HOUR, 10 * DateTime.HOUR);
    }
}
