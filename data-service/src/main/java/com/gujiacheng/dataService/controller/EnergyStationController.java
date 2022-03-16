package com.gujiacheng.dataService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gujiacheng.dataService.controller.utils.R;
import com.gujiacheng.dataService.controller.utils.chartUtils.Edata;
import com.gujiacheng.dataService.controller.utils.chartUtils.Series;
import com.gujiacheng.dataService.pojo.EnergyStationData;
import com.gujiacheng.dataService.service.EnergyStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制查询能源站数据的接口，其中限制查询数量的方法.last可能会有sql注入问题。
 * 由于数据库表没有主键，如果需要查询可能耗时较大
 */
@RestController
public class EnergyStationController {

    @Autowired
    EnergyStationService energyStationService;

    /**
     * 获取能源站所有数据
     * @return 返回查询到的数据结果集
     */
    @GetMapping()
    public R getAll() {
        return new R(true, energyStationService.list());
    }

    /**
     * 单独查询数据，针对温度
     * @param limit 限制查询到的数据量
     * @return 返回由温度构成的数据json
     */
    @GetMapping("/temp")
    public R getTemperature(Integer limit) {
        ArrayList<String> legends = new ArrayList<>();
        legends.add("温度");
        ArrayList<String> xSeries = new ArrayList<>();
        ArrayList<Series> series = new ArrayList<>();
        QueryWrapper<EnergyStationData> queryWrapper= new QueryWrapper<>();
        queryWrapper.select("first_sampling_point_temperature","first_data_collection_time");
        queryWrapper.last("limit "+limit.toString());
        List<EnergyStationData> energyStationTempData = energyStationService.list(queryWrapper);
        ArrayList<Object> sample = new ArrayList<>();
        for (EnergyStationData data : energyStationTempData) {
            xSeries.add(data.getFirstDataCollectionTime());
            sample.add(data.getFirstSamplingPointTemperature());
        }
        Series tempSeries = new Series(legends.get(0), "line", sample);
        series.add(tempSeries);
        Edata edata = new Edata(legends, xSeries, series);
        return new R(true, edata);
    }

    /**
     * 单独查询数据，针对风速以及风向
     * @param limit 限制查询到的数据量
     * @return 返回由温度构成的数据json
     */
    @GetMapping("/wind")
    public R getWind(Integer limit) {
        ArrayList<String> legends = new ArrayList<>();
        legends.add("风速");
        legends.add("风向");
        ArrayList<String> xSeries = new ArrayList<>();
        ArrayList<Series> series = new ArrayList<>();
        QueryWrapper<EnergyStationData> queryWrapper= new QueryWrapper<>();
        queryWrapper.select("first_sampling_point_wind_speed","first_sampling_point_wind_direction","first_data_collection_time");
        queryWrapper.last("limit "+limit.toString());
        List<EnergyStationData> energyStationTempData = energyStationService.list(queryWrapper);
        for (EnergyStationData data : energyStationTempData) {
            xSeries.add(data.getFirstDataCollectionTime());
        }
        ArrayList<Object> sample = new ArrayList<>();
        for (EnergyStationData data : energyStationTempData) {
            sample.add(data.getFirstSamplingPointWindSpeed());
        }
        series.add(new Series(legends.get(0), "line", sample));
        sample = new ArrayList<>();
        for (EnergyStationData data : energyStationTempData) {
            sample.add(data.getFirstSamplingPointWindSpeed());
        }
        series.add(new Series(legends.get(1), "line", sample));
        return new R(true, new Edata(legends, xSeries, series));
    }
    /**
     * 单独查询数据，针对电压查询
     * @param limit 限制查询到的数据量
     * @return 返回由温度构成的数据json
     */
    @GetMapping("/battery")
    public R getBattery(Integer limit) {
        ArrayList<String> legends = new ArrayList<>();
        legends.add("电压");
        ArrayList<String> xSeries = new ArrayList<>();
        ArrayList<Series> series = new ArrayList<>();
        QueryWrapper<EnergyStationData> queryWrapper= new QueryWrapper<>();
        queryWrapper.select("battery_voltage","first_data_collection_time");
        queryWrapper.last("limit "+limit.toString());
        List<EnergyStationData> energyStationTempData = energyStationService.list(queryWrapper);
        ArrayList<Object> sample = new ArrayList<>();
        for (EnergyStationData data : energyStationTempData) {
            xSeries.add(data.getFirstDataCollectionTime());
            sample.add(data.getBatteryVoltage());
        }
        Series tempSeries = new Series(legends.get(0), "line", sample);
        series.add(tempSeries);
        Edata edata = new Edata(legends, xSeries, series);
        return new R(true, edata);
    }
    @GetMapping("/pressure")
    public R getPressure(Integer limit) {
        ArrayList<String> legends = new ArrayList<>();
        legends.add("气压");
        ArrayList<String> xSeries = new ArrayList<>();
        ArrayList<Series> series = new ArrayList<>();
        QueryWrapper<EnergyStationData> queryWrapper= new QueryWrapper<>();
        queryWrapper.select("first_sampling_point_atmospheric_pressure","first_data_collection_time");
        queryWrapper.last("limit "+limit.toString());
        List<EnergyStationData> energyStationTempData = energyStationService.list(queryWrapper);
        ArrayList<Object> sample = new ArrayList<>();
        for (EnergyStationData data : energyStationTempData) {
            xSeries.add(data.getFirstDataCollectionTime());
            sample.add(data.getFirstSamplingPointAtmosphericPressure());
        }
        Series tempSeries = new Series(legends.get(0), "line", sample);
        series.add(tempSeries);
        Edata edata = new Edata(legends, xSeries, series);
        return new R(true, edata);
    }

    @GetMapping("/test")
    public R testUtils() {
        ArrayList<String> legends = new ArrayList<>();
        legends.add("第一个");
        legends.add("B");
        ArrayList<String> xSeries = new ArrayList<>();
        xSeries.add("2022-1-3");
        xSeries.add("2022-1-4");
        xSeries.add("2022-1-5");
        xSeries.add("2022-1-6");
        ArrayList<Series> series = new ArrayList<>();
        for (String legend : legends) {
            ArrayList<Object> sample = new ArrayList<>();
            for (String s : xSeries) {
                sample.add(Math.random()*100);
            }
            Series tempSeries = new Series(legend, "line", sample);
            series.add(tempSeries);
        }
        Edata edata = new Edata(legends, xSeries, series);
        return new R(true, edata);
    }

}
