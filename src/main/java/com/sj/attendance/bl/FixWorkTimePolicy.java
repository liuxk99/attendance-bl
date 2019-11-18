package com.sj.attendance.bl;

class FixWorkTimePolicy {
    // 名称，比如：XX集团-固定工时-全天
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

    @Override
    public String toString() {
        return name + ":\n" +
                "checkIn " + DateTime.timeToString(checkInTime) + "\n" +
                "duration " + DateTime.timeToString(duration) + "\n" +
                "checkOut " + DateTime.timeToString(checkOutTime()) + "\n";
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
