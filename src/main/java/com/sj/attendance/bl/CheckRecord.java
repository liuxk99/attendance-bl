package com.sj.attendance.bl;

import com.fasterxml.uuid.Generators;

import java.util.Date;
import java.util.UUID;

public class CheckRecord {
    // uuid
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    private UUID uuid = Generators.timeBasedGenerator().generate();

    // id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id = -1L;

    public String policySetName;
    public FixWorkTimePolicy policy;
    public Date realCheckInTime;
    public Date realCheckOutTime;

    public CheckRecord(String policySetName, Date realCheckInDate, Date realCheckOutDate, FixWorkTimePolicy policy) {
        this.policySetName = policySetName;
        this.policy = policy;
        if (policy != null) {
            this.policyUuid = policy.getUuid();
        }
        this.realCheckInTime = realCheckInDate;
        this.realCheckOutTime = realCheckOutDate;
    }

    public CheckRecord(String policySetName, Date realCheckInDate, Date realCheckOutDate, UUID policyUuid) {
        this.policySetName = policySetName;
        this.policyUuid = policyUuid;
        this.realCheckInTime = realCheckInDate;
        this.realCheckOutTime = realCheckOutDate;
    }

    @Override
    public String toString() {
        final String LF = "\n";
        String sb = getClass().getSimpleName() + "{\n" + "name: " + policySetName + LF +
                "id: " + getId() + LF +
                "uuid: " + getUuid() + LF +
                "policyUuid: " + policyUuid + LF +
                "realCheckIn: " + realCheckInTime + LF +
                "realCheckOut: " + realCheckOutTime + LF +
                "}\n";
        return sb;
    }

    public boolean isLate() {
        if (policy != null && realCheckInTime != null)
            return policy.isLate(realCheckInTime);
        return false;
    }

    public boolean isEarlyLeave() {
        if (policy != null && realCheckOutTime != null)
            return policy.isEarlyLeave(realCheckOutTime);

        return false;
    }

    public UUID getPolicyUuid() {
        return policyUuid;
    }

    public void setPolicyUuid(UUID policyUuid) {
        this.policyUuid = policyUuid;
    }

    private UUID policyUuid;

    public static CheckRecord randomInstance(String policySetName, FixWorkTimePolicy policy) {
        CheckRecord checkRecord;
        {
            Date realCheckInDate = randomCheckInTime(policy);
            Date realCheckOutDate = randomCheckOutTime(policy);

            checkRecord = new CheckRecord(policySetName,
                    realCheckInDate, realCheckOutDate,
                    policy);
        }
        return checkRecord;
    }

    private static Date randomCheckInTime(FixWorkTimePolicy policy) {
        Date realCheckInDate;
        {
            long realCheckInTime = TimeUtils.getDayDate(new Date()) + policy.randomCheckInTime();
            realCheckInDate = new Date();
            realCheckInDate.setTime(realCheckInTime);
        }
        return realCheckInDate;
    }

    public static Date randomCheckOutTime(FixWorkTimePolicy policy) {
        Date realCheckOutDate;
        {
            long realCheckOutTime = TimeUtils.getDayDate(new Date()) + policy.randomCheckOutTime();
            realCheckOutDate = new Date();
            realCheckOutDate.setTime(realCheckOutTime);

        }
        return realCheckOutDate;
    }
}
