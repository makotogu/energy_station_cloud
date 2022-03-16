package com.gujiacheng.dataService.controller.utils.chartUtils;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Series {
    private String name;
    private String type;
    private ArrayList<Object> data;

    public Series() {
    }

    public Series(String name, String type, ArrayList<Object> data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
