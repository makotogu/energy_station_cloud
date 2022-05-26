package com.gujiacheng.dataService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gujiacheng.dataService.mapper.EnergyStationMapper;
import com.gujiacheng.dataService.pojo.EnergyStationData;
import com.gujiacheng.dataService.service.EnergyStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EnergyStationServiceImpl  extends ServiceImpl < EnergyStationMapper, EnergyStationData > implements EnergyStationService {

    private static final String NO_TARGET = "all";
    private static final String TIME_TARGET  = "first_data_collection_time";
    private static final String NO_SEARCH_CONTENT = "none";

    @Autowired
    private EnergyStationMapper energyStationMapper;
    @Override
    public Page<EnergyStationData> getPagedContents(Integer page, Integer size, String target, String orderTarget, Boolean order, String key) {
        Page<EnergyStationData> energyStationDataPage = new Page<>(page, size);
        QueryWrapper<EnergyStationData> energyStationDataQueryWrapper = new QueryWrapper<>();
        if (!Objects.equals(orderTarget, NO_TARGET) && (!Objects.equals(orderTarget, TIME_TARGET))) {
            if (order) {
                energyStationDataQueryWrapper.orderByDesc(target);
            } else {
                energyStationDataQueryWrapper.orderByAsc(target);
            }
        }
        if (!Objects.equals(key, NO_SEARCH_CONTENT) && (!Objects.equals(target, NO_TARGET))) {
            energyStationDataQueryWrapper.like(target, key);
        }
        return page(energyStationDataPage, energyStationDataQueryWrapper);
    }

    @Override
    public Object getXinHongQiaoChart() {
        ArrayList<List<EnergyStationData>> energyStationDataArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            QueryWrapper<EnergyStationData> wrapper = new QueryWrapper<>();
            wrapper.eq("collect_node",i+1);
            wrapper.select("battery_voltage", "first_sampling_point_temperature","first_sampling_point_atmospheric_pressure");
            List<EnergyStationData> energyStationData = energyStationMapper.selectList(wrapper);
            energyStationDataArrayList.add(energyStationData);
        }
        return energyStationDataArrayList;
    }

    @Override
    public Object getWaiGaoQiaoChart() {
        ArrayList<List<EnergyStationData>> energyStationDataArrayList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            QueryWrapper<EnergyStationData> wrapper = new QueryWrapper<>();
            wrapper.eq("collect_node",i+1);
            wrapper.select("battery_voltage", "first_sampling_point_temperature","first_sampling_point_atmospheric_pressure");
            List<EnergyStationData> energyStationData = energyStationMapper.selectList(wrapper);
            energyStationDataArrayList.add(energyStationData);
        }
        return energyStationDataArrayList;
    }

    @Override
    public Object getZhangJiangChart() {
        ArrayList<List<EnergyStationData>> energyStationDataArrayList = new ArrayList<>();
        for (int i = 2; i < 5; i++) {
            QueryWrapper<EnergyStationData> wrapper = new QueryWrapper<>();
            wrapper.eq("collect_node",i+1);
            wrapper.select("battery_voltage", "first_sampling_point_temperature","first_sampling_point_atmospheric_pressure");
            List<EnergyStationData> energyStationData = energyStationMapper.selectList(wrapper);
            energyStationDataArrayList.add(energyStationData);
        }
        return energyStationDataArrayList;
    }
}
