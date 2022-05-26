package com.gujiacheng.dataService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.HashBasedTable;
import com.gujiacheng.dataService.clients.SystemDataClient;
import com.gujiacheng.dataService.controller.utils.R;
import com.gujiacheng.dataService.pojo.EnergyStationData;
import com.gujiacheng.dataService.service.EnergyStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class HomePageDataController {

    @Autowired
    private EnergyStationService energyStationService;
    @Autowired
    private SystemDataClient systemDataClient;

    @GetMapping("/home")
    public R HomePageDataSets() throws JsonProcessingException {
        QueryWrapper<EnergyStationData> queryWrapper = new QueryWrapper<>();
        ObjectMapper objectMapper = new ObjectMapper();
        queryWrapper.last("limit 3");
        List<EnergyStationData> list = energyStationService.list(queryWrapper);
        HashBasedTable<String, String, String> table = HashBasedTable.create();
        for (EnergyStationData energyStationData : list) {
            table.put("node", String.valueOf(energyStationData.getCollectNode()), String.valueOf(energyStationData.getNodeStatus()));
            table.put("table", String.valueOf(energyStationData.getCollectNode()), objectMapper.writeValueAsString(energyStationData));
        }
        table.put("test","test", systemDataClient.test().toString());
        return new R(true, table.rowMap());
    }
}
