package com.sj.attendance.bl;

public interface ConfigPersist {
    WorkTimePolicySetConfig load();
    boolean save(WorkTimePolicySetConfig config);
}
