package com.sj.attendance.bl;

// 弹性工时
public class FlexWorkTimePolicy extends FixWorkTimePolicy {
    private long latestCheckInTime;
    private long realCheckInTime = 0L;

    public long getLatestCheckInTime() {
        return latestCheckInTime;
    }

    FlexWorkTimePolicy(String name, long checkInTime, long duration, long latestCheckInTime) {
        super(name, checkInTime, duration);

        this.latestCheckInTime = latestCheckInTime;
    }

    public void setRealCheckInTime(long realCheckInTime) {
        this.realCheckInTime = realCheckInTime;
    }

    @Override
    public long getCheckOutTime() {
        return realCheckInTime + duration;
    }

    @Override
    public boolean isLate(long realCheckInTime) {
        return realCheckInTime > this.latestCheckInTime;
    }

    @Override
    public boolean isEarlyLeave(long realCheckOutTime) {
        return realCheckOutTime < this.realCheckInTime + duration;
    }

    @Override
    public String toString() {
        return name + ":\n" +
                "checkIn " + DateTime.timeToString(checkInTime) + "\n" +
                "latest " + DateTime.timeToString(latestCheckInTime) + "\n" +
                "duration " + DateTime.timeToString(duration) + "\n" +
                "real CheckIn " + DateTime.timeToString(realCheckInTime) + "\n" +
                "checkOut " + DateTime.timeToString(getCheckOutTime()) + "\n";
    }

    public String toShortString() {
        return title + ":\n" +
                "checkIn " + DateTime.timeToString(checkInTime) + "\n" +
                "latest " + DateTime.timeToString(latestCheckInTime) + "\n" +
                "duration " + DateTime.timeToString(duration) + "\n" +
                "real CheckIn " + DateTime.timeToString(realCheckInTime) + "\n" +
                "checkOut " + DateTime.timeToString(getCheckOutTime()) + "\n";
    }
}
