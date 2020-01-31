package com.sj.attendance.bl;

import com.sj.time.DateTimeUtils;

import java.util.Date;
import java.util.Locale;

// 弹性工时
public class FlexWorkTimePolicy extends FixWorkTimePolicy {
    private long latestCheckInTime;
    private long realCheckInTime = 0L;

    public long getLatestCheckInTime() {
        return latestCheckInTime;
    }

    public FlexWorkTimePolicy(String name, String shortName, long checkInTime, long duration, long latestCheckInTime) {
        super(name, shortName, checkInTime, duration);
        this.latestCheckInTime = latestCheckInTime;

        this.clazz = FlexWorkTimePolicy.class.getSimpleName();
    }

    public void setRealCheckInTime(long refTime) {
        this.realCheckInTime = refTime;
    }

    public void setRealCheckInTime(Date date) {
        this.realCheckInTime = DateTimeUtils.getDayTime(date);
    }

    @Override
    public long getCheckOutTime() {
        long checkIn = checkInTime;
        if (realCheckInTime != 0L) {
            checkIn = Math.max(realCheckInTime, checkInTime);
        }
        return checkIn + duration;
    }

    @Override
    public boolean isLate(long realCheckInTime) {
        return realCheckInTime > latestCheckInTime;
    }

    @Override
    public boolean isLate(Date realCheckInDate) {
        return DateTimeUtils.getDayTime(realCheckInDate) > latestCheckInTime;
    }

    @Override
    public boolean isEarlyLeave(long realCheckOutTime) {
        return realCheckOutTime < getCheckOutTime();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FlexWorkTimePolicy) {
            FlexWorkTimePolicy policy = (FlexWorkTimePolicy) o;
            return policy.uuid.equals(this.uuid);
        }
        return false;
    }

    @Override
    public String toString() {
        return name + ":\n" +
                "uuid: " + uuid + "\n" +
                "checkIn: " + toCheckIn() + "\n" +
                "checkOut: " + toCheckOut() + "\n" +
                "real CheckIn: " + DateTimeUtils.formatRefTime(realCheckInTime) + "\n" +
                "plan checkOut: " + DateTimeUtils.formatRefTime(getCheckOutTime()) + "\n";
    }

    public String toShortString() {
        return shortName + ":\n" +
                "checkIn: " + toCheckIn() + "\n" +
                "checkOut: " + toCheckOut() + "\n" +
                "duration: " + DateTimeUtils.formatRefTime(duration) + "\n" +
                "plan checkOut: " + DateTimeUtils.formatRefTime(getCheckOutTime()) + "\n";
    }

    @Override
    public String toCheckIn() {
        final String checkInStr = DateTimeUtils.formatRefTime(checkInTime);
        final String latestCheckInStr = DateTimeUtils.formatRefTime(latestCheckInTime);
        return String.format(Locale.getDefault(), "%s~%s", checkInStr, latestCheckInStr);
    }


    public String toCheckOut() {
        final String checkInStr = DateTimeUtils.formatRefTime(checkInTime + duration);
        final String latestCheckInStr = DateTimeUtils.formatRefTime(latestCheckInTime + duration);
        return String.format(Locale.getDefault(), "%s~%s", checkInStr, latestCheckInStr);
    }

    @Override
    public long getPlanCheckOutTime(Date checkInDate) {
        setRealCheckInTime(checkInDate);
        return super.getPlanCheckOutTime(checkInDate);
    }
}
