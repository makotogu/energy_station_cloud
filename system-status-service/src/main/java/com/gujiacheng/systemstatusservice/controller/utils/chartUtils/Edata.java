package com.gujiacheng.systemstatusservice.controller.utils.chartUtils;

import lombok.Data;

import java.util.ArrayList;

/**
 * 图表用封装函数，将数据处理成便于给图表使用的格式
 */
@Data
public class Edata {
    private ArrayList<String> legend;
    private ArrayList<String> xSeries;
    private ArrayList<Series> series;

    public Edata () {}

    public Edata(ArrayList<String> legend, ArrayList<String> xSeries, ArrayList<Series> series) {
        this.legend = legend;
        this.xSeries = xSeries;
        this.series = series;
    }



}
