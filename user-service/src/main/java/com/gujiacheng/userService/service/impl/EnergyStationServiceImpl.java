package com.gujiacheng.userService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gujiacheng.userService.mapper.EnergyStationMapper;
import com.gujiacheng.userService.pojo.EnergyStationData;
import com.gujiacheng.userService.service.EnergyStationService;
import org.springframework.stereotype.Service;

@Service
public class EnergyStationServiceImpl  extends ServiceImpl < EnergyStationMapper, EnergyStationData > implements EnergyStationService {
}
