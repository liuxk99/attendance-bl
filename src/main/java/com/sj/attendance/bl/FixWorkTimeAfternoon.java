package com.sj.attendance.bl;

// 固定工作时间下午半天
class FixWorkTimeAfternoon extends WorkTimeBase {

    FixWorkTimeAfternoon() {
        // 14:00到18:00，中间4个小时
        super(14 * DateTime.HOUR, 4 * DateTime.HOUR);
    }
}
