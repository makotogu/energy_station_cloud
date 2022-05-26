package com.gujiacheng.systemstatusservice.service.impl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.gujiacheng.systemstatusservice.service.SystemService;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.text.DecimalFormat;

@Service
public class SystemServiceImpl implements SystemService {

    @Override
    public Table<String, String, Object> getSystemStatus() throws InterruptedException {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        CentralProcessor processor = hardware.getProcessor();
        Table<String, String, Object> table = HashBasedTable.create();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Thread.sleep(1*500);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
                - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
                - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
                - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()]
                - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
                - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
                - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;

        table.put("cpu", "系统占用率",cSys * 1.0 / totalCpu);
        table.put("memory", "内存占用率", hardware.getMemory().getAvailable() * 1.0 / hardware.getMemory().getTotal());

        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        FileSystem fileSystem = operatingSystem.getFileSystem();
        OSFileStore osFileStore = fileSystem.getFileStores().get(0);
        table.put("os", "硬盘占用率", 1 - osFileStore.getFreeSpace() * 1.0 / osFileStore.getTotalSpace() );
        return table;
    }
}
