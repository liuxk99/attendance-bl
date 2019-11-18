package com.sj.attendance.bl;

// 弹性工作时间
class FlexWorkTimePolicy extends FixWorkTimePolicy {
    private long latestCheckInTime;
    private long realCheckInTime = 0L;

    FlexWorkTimePolicy(String name, long checkInTime, long duration, long latestCheckInTime) {
        super(name, checkInTime, duration);

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

    @Override
    public String toString() {
        return name + ":\n" +
                "checkIn " + DateTime.timeToString(checkInTime) + "\n" +
                "latest " + DateTime.timeToString(latestCheckInTime) + "\n" +
                "duration " + DateTime.timeToString(duration) + "\n" +
                "real CheckIn " + DateTime.timeToString(realCheckInTime) + "\n" +
                "checkOut " + DateTime.timeToString(checkOutTime()) + "\n";
    }
}
