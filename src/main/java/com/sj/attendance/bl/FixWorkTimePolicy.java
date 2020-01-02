package com.sj.attendance.bl;

import java.util.Date;

public class FixWorkTimePolicy {
    public String getName() {
        return name;
    }

    // 名称，比如：XX集团-固定工时-全天
    String name;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    String shortName;

    // 上班时间
    long checkInTime;

    public long getDuration() {
        return duration;
    }

    // 工作时长
    long duration;

    public long getCheckInTime() {
        return checkInTime;
    }

    public FixWorkTimePolicy(String name, long checkInTime, long duration) {
        this.name = name;
        this.checkInTime = checkInTime;
        this.duration = duration;
    }

    public FixWorkTimePolicy(String name, String shortName, long checkInTime, long duration) {
        this.name = name;
        this.shortName = shortName;
        this.checkInTime = checkInTime;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return name + ":\n" +
                "shortName " + shortName + "\n" +
                "checkIn " + TimeUtils.formatRefTime(checkInTime) + "\n" +
                "duration " + TimeUtils.formatRefTime(duration) + "\n" +
                "checkOut " + TimeUtils.formatRefTime(getCheckOutTime()) + "\n";
    }

    public String toShortString() {
        return shortName + ":\n" +
                "checkIn " + TimeUtils.formatRefTime(checkInTime) + "\n" +
                "duration " + TimeUtils.formatRefTime(duration) + "\n" +
                "checkOut " + TimeUtils.formatRefTime(getCheckOutTime()) + "\n";
    }

    public long getCheckOutTime() {
        return checkInTime + duration;
    }

    public boolean isLate(long realCheckInTime) {
        return realCheckInTime > checkInTime;
    }

    public boolean isLate(Date realCheckInDate) {
        return TimeUtils.getDayTime(realCheckInDate) > checkInTime;
    }

    // 早退
    public boolean isEarlyLeave(long realCheckOutTime) {
        return realCheckOutTime < this.getCheckOutTime();
    }

    // 早退
    public boolean isEarlyLeave(Date realCheckOutDate) {
        long realCheckOutTime = TimeUtils.getDayTime(realCheckOutDate);
        return realCheckOutTime < this.getCheckOutTime();
    }

    public String toCheckIn() {
        return TimeUtils.formatRefTime(checkInTime);
    }

    public String toCheckOut() {
        return TimeUtils.formatRefTime(getCheckOutTime());
    }

    public long getPlanCheckOutTime(Date checkInDate) {
        return TimeUtils.getDayDate(checkInDate) + getCheckOutTime();
    }
}
