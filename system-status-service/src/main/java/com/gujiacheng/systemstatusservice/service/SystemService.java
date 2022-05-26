package com.gujiacheng.systemstatusservice.service;

import com.google.common.collect.Table;

public interface SystemService {

    Table<String, String, Object> getSystemStatus() throws InterruptedException;
}
