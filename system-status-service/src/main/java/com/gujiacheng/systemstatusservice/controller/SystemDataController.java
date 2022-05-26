package com.gujiacheng.systemstatusservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.gujiacheng.systemstatusservice.controller.utils.R;
import com.gujiacheng.systemstatusservice.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SystemDataController {

    @Autowired
    private SystemService systemService;

    /**
     * get system data
     * @return
     */
    @GetMapping("/systemData")
    public R systemData() throws InterruptedException {
        Table<String, String, Object> table = systemService.getSystemStatus();
        return new R(true, table.cellSet());
    }

}
