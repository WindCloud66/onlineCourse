package com.houyongju.staservice.client;

import com.houyongju.commonutils.ResultMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author HouYongJu
 * @create 2021-08-22 14:45
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping("counterRegister/{day}")
    public ResultMessage countRegister(@PathVariable("day") String day);

}
