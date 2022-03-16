package com.gujiacheng.dataService.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author gujiacheng
 * @date 2022-1-13
 *
 */
@Data
@TableName("neng_yuan_zhan")
public class EnergyStationData {
    private String collectNode;
    private String nodeType;
    private String nodeStatus;
    private String batteryVoltage;
    private String dataNumber;
    private String dataLength;
    private String firstDataCollectionTime;
    private String collectInterval;
    private String firstSamplingPointTemperature;
    private String firstSamplingPointHumidity;
    private String receiveTime;
    private String softwareVersionNumber;
    private String firstSamplingPointAtmosphericPressure;
    private String firstSamplingPointWindSpeed;
    private String firstSamplingPointWindDirection;
}
