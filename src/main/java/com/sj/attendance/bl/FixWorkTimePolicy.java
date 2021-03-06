package com.sj.attendance.bl;

import com.fasterxml.uuid.Generators;
import com.google.gson.annotations.SerializedName;
import com.sj.time.DateTimeUtils;

import java.util.Date;
import java.util.UUID;

public class FixWorkTimePolicy {
    public static final String TAG = "clazz";
    @SerializedName(TAG)
    protected String clazz = FixWorkTimePolicy.class.getSimpleName();

    // uuid
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    UUID uuid = Generators.timeBasedGenerator().generate();

    // id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

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

    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(FixWorkTimePolicy.class)){
            FixWorkTimePolicy policy = (FixWorkTimePolicy) o;
            return this.uuid.equals(policy.uuid);
        }
        return false;
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
                "uuid: " + uuid + "\n" +
                "checkIn " + DateTimeUtils.formatRefTime(checkInTime) + "\n" +
                "duration " + DateTimeUtils.formatRefTime(duration) + "\n" +
                "checkOut " + DateTimeUtils.formatRefTime(getCheckOutTime()) + "\n";
    }

    public String toShortString() {
        return shortName + ":\n" +
                "checkIn " + DateTimeUtils.formatRefTime(checkInTime) + "\n" +
                "duration " + DateTimeUtils.formatRefTime(duration) + "\n" +
                "checkOut " + DateTimeUtils.formatRefTime(getCheckOutTime()) + "\n";
    }

    public long getCheckOutTime() {
        return checkInTime + duration;
    }

    public boolean isLate(long realCheckInTime) {
        return realCheckInTime > checkInTime;
    }

    public boolean isLate(Date realCheckInDate) {
        return DateTimeUtils.getDayTime(realCheckInDate) > checkInTime;
    }

    // 早退
    public boolean isEarlyLeave(long realCheckOutTime) {
        return realCheckOutTime < this.getCheckOutTime();
    }

    // 早退
    public boolean isEarlyLeave(Date realCheckOutDate) {
        long realCheckOutTime = DateTimeUtils.getDayTime(realCheckOutDate);
        return realCheckOutTime < this.getCheckOutTime();
    }

    public String toCheckIn() {
        return DateTimeUtils.formatRefTime(checkInTime);
    }

    public String toCheckOut() {
        return DateTimeUtils.formatRefTime(getCheckOutTime());
    }

    public long getPlanCheckOutTime(Date checkInDate) {
        return DateTimeUtils.getDayDate(checkInDate) + getCheckOutTime();
    }

    public long randomCheckOutTime() {
        long checkOutOffset = (long) ((Math.random() - 0.5) * DateTimeUtils.HOUR);
        return getCheckOutTime() + checkOutOffset;
    }

    public long randomCheckInTime() {
        long checkInOffset = (long) ((Math.random() - 0.5) * DateTimeUtils.HOUR);
        return checkInTime + checkInOffset;
    }
}
