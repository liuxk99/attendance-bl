package com.sj.attendance.bl;

// 弹性工作时间
class FlexibleWorkTime extends WorkTimeBase {
    private long latestCheckInTime;
    private long realCheckInTime = 0L;

    FlexibleWorkTime(long checkInTime, long duration, long latestCheckInTime) {
        super(checkInTime, duration);

        this.latestCheckInTime = latestCheckInTime;
    }

    void setRealCheckInTime(long realCheckInTime) {
        this.realCheckInTime = realCheckInTime;
    }

    @Override
    long checkOutTime() {
        return realCheckInTime + duration;
    }

    @Override
    boolean isLate(long realCheckInTime) {
        return realCheckInTime > this.latestCheckInTime;
    }

    @Override
    boolean isEarlyLeave(long realCheckOutTime) {
        return realCheckOutTime < this.realCheckInTime + duration;
    }
}
