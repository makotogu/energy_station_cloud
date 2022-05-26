package com.gujiacheng.dataService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gujiacheng.dataService.controller.utils.R;
import com.gujiacheng.dataService.controller.utils.chartUtils.Edata;
import com.gujiacheng.dataService.controller.utils.chartUtils.Series;
import com.gujiacheng.dataService.pojo.EnergyStationData;
import com.gujiacheng.dataService.service.EnergyStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/query/{page}/{size}")
    public R getPage(@PathVariable("page") Integer page,
                     @PathVariable("size") Integer size,
                     @RequestParam(value = "target", defaultValue = "all") String target,
                     @RequestParam(value = "orderTarget", defaultValue = "all") String orderTarget,
                     @RequestParam(value = "order", defaultValue = "true") Boolean order,
                     @RequestParam(value = "key", defaultValue = "none") String key) {

        Page<EnergyStationData> pagedContents = energyStationService.getPagedContents(page, size, target, orderTarget, order, key);
        return new R(true, pagedContents);
    }

    @GetMapping("/charts/{struct}")
    public R getChart(@PathVariable("struct") String struct) {
        switch (struct) {
            case "ZHANG_JIANG": {
                return new R(true, energyStationService.getZhangJiangChart());
            }
            case "WAI_GAO_QIAO": {
                return new R(true, energyStationService.getWaiGaoQiaoChart());
            }
            case "XIN_HONG_QIAO": {
                return new R(true, energyStationService.getXinHongQiaoChart());
            }
            default: {
                return new R(false);
            }
        }
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
