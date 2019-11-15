package com.sj.attendance.bl;

public class WorkTimePolicy {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MILLISECONDS_PER_SECOND = 1000;

    private static final long SECOND = MILLISECONDS_PER_SECOND;
    private static final long MINUTE = SECOND * SECONDS_PER_MINUTE;
    private static final long HOUR = MINUTE * MINUTES_PER_HOUR;
    public static final long DAY = HOUR * HOURS_PER_DAY;

    // 固定工作时间
    abstract class WorkTimeBase {
        // 上班时间
        long checkInTime;

        // 工作时长
        long duration;

        WorkTimeBase(long checkInTime, long duration) {
            this.checkInTime = checkInTime;
            this.duration = duration;
        }

        long checkOutTime() {
            return checkInTime + duration * HOUR;
        }

        boolean isLate(long checkInTime) {
            return checkInTime > checkOutTime();
        }

        // 早退
        boolean isEarlyLeave(long checkOutTime) {
            return checkOutTime < this.checkOutTime();
        }
    }

    // 固定工作时间
    class FixWorkTime extends WorkTimeBase {
        FixWorkTime() {
            // 早09:00到晚06:00，中间9个小时
            super(9 * HOUR, 9 * HOUR);
        }
    }

    // 弹性工作时间
    class FlexibleWorkTime extends WorkTimeBase {
        long latestCheckInTime;
        long realCheckInTime = 0L;

        FlexibleWorkTime(long checkInTime, long duration, long latestCheckInTime) {
            super(checkInTime, duration);

            this.latestCheckInTime = latestCheckInTime;
        }

        void setRealCheckInTime(long realCheckInTime) {
            this.realCheckInTime = realCheckInTime;
        }

        boolean isLate(long checkInTime){
            return checkInTime > this.latestCheckInTime;
        }

        boolean isEarlyLeave(long checkOutTime){
            return checkOutTime < this.realCheckInTime + duration * HOUR;
        }
    }
}
