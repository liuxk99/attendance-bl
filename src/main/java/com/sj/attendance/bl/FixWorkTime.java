package com.sj.attendance.bl;

// 固定工作时间
class FixWorkTime extends WorkTimeBase {
    FixWorkTime() {
        // 早09:00到晚06:00，中间9个小时
        super(9 * DateTime.HOUR, 9 * DateTime.HOUR);
    }
}
