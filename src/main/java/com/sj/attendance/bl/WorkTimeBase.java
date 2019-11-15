package com.sj.attendance.bl;

class WorkTimeBase {
    // 上班时间
    long checkInTime;

    // 工作时长
    long duration;

    WorkTimeBase(long checkInTime, long duration) {
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
