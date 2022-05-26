package com.gujiacheng.dataService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gujiacheng.dataService.pojo.EnergyStationData;
import org.springframework.stereotype.Service;

@Service
public interface EnergyStationService extends IService<EnergyStationData> {
    Page<EnergyStationData> getPagedContents(Integer page, Integer size, String target, String orderTarget, Boolean order, String key);

    Object getXinHongQiaoChart();

    Object getWaiGaoQiaoChart();

    Object getZhangJiangChart();
}
