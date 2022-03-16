package com.gujiacheng.dataService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gujiacheng.dataService.mapper.EnergyStationMapper;
import com.gujiacheng.dataService.pojo.EnergyStationData;
import com.gujiacheng.dataService.service.EnergyStationService;
import org.springframework.stereotype.Service;

@Service
public class EnergyStationServiceImpl  extends ServiceImpl < EnergyStationMapper, EnergyStationData > implements EnergyStationService {
}
