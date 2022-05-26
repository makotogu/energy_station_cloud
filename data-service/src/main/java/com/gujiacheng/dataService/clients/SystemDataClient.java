package com.gujiacheng.dataService.clients;

import com.gujiacheng.dataService.controller.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("system-status-service")
public interface SystemDataClient {

    @GetMapping("/test")
    R test();
}
