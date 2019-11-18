package com.sj.attendance.bl;

class FixWorkTimePolicy {
    // 名称，比如：乐融集团-固定工时-全天
    String name;

    // 上班时间
    long checkInTime;

    // 工作时长
    long duration;

    FixWorkTimePolicy(String name, long checkInTime, long duration) {
        this.name = name;
        this.checkInTime = checkInTime;
        this.duration = duration;
    }

    long checkOutTime() {
        return checkInTime + duration;
    }

    boolean isLate(long realCheckInTime) {
        return realCheckInTime > checkInTime;
    }

    // 早退
    boolean isEarlyLeave(long realCheckOutTime) {
        return realCheckOutTime < this.checkOutTime();
    }
}
