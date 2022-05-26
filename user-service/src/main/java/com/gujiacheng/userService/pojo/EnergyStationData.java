package com.gujiacheng.userService.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author gujiacheng
 * @date 2022-1-13
 *
 */
@Data
@TableName("neng_yuan_zhan_1")
public class EnergyStationData {
    private Integer collectNode;
    private Integer nodeType;
    private Integer nodeStatus;
    private Integer batteryVoltage;
    private Integer dataNumber;
    private Integer dataLength;
    private String firstDataCollectionTime;
    private Integer collectInterval;
    private Double firstSamplingPointTemperature;
    private Integer firstSamplingPointHumidity;
    private Double firstSamplingPointAtmosphericPressure;
    private Integer firstSamplingPointWindSpeed;
    private String firstSamplingPointWindDirection;
    private String receiveTime;
    private String softwareVersionNumber;
}
