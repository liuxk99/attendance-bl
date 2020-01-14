package com.sj.attendance.bl;

import com.fasterxml.uuid.Generators;

import java.util.Date;
import java.util.UUID;

public class CheckRecord {
    // uuid
    public UUID getUuid() {
        if (uuid == null) {
            uuid = Generators.timeBasedGenerator().generate();
        }
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    private UUID uuid;

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

    public CheckRecord(String policySetName, FixWorkTimePolicy policy, Date realCheckInDate, Date realCheckOutDate) {
        this.policySetName = policySetName;
        this.policy = policy;
        this.realCheckInTime = realCheckInDate;
        this.realCheckOutTime = realCheckOutDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName() + "{\n");
        sb.append("name: " + policySetName + "\n")
                .append("realCheckIn: " + realCheckInTime + "\n")
                .append("realCheckOut: " + realCheckOutTime + "\n")
                .append("}\n");
        return sb.toString();
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
}
