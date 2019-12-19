package com.sj.attendance.bl;

public class FixWorkTimePolicy {
    public String getName() {
        return name;
    }

    // 名称，比如：XX集团-固定工时-全天
    String name;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title;

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

    @Override
    public String toString() {
        return name + ":\n" +
                "checkIn " + DateTime.formatRefTime(checkInTime) + "\n" +
                "duration " + DateTime.formatRefTime(duration) + "\n" +
                "checkOut " + DateTime.formatRefTime(getCheckOutTime()) + "\n";
    }

    public String toShortString() {
        return title + ":\n" +
                "checkIn " + DateTime.formatRefTime(checkInTime) + "\n" +
                "duration " + DateTime.formatRefTime(duration) + "\n" +
                "checkOut " + DateTime.formatRefTime(getCheckOutTime()) + "\n";
    }

    public long getCheckOutTime() {
        return checkInTime + duration;
    }

    public boolean isLate(long realCheckInTime) {
        return realCheckInTime > checkInTime;
    }

    // 早退
    public boolean isEarlyLeave(long realCheckOutTime) {
        DateTime.formatRefTime(realCheckOutTime);
        DateTime.formatRefTime(getCheckOutTime());
        return realCheckOutTime < this.getCheckOutTime();
    }

    public String toCheckIn() {
        return DateTime.formatRefTime(checkInTime);
    }

    public String toCheckOut() {
        return DateTime.formatRefTime(getCheckOutTime());
    }
}
