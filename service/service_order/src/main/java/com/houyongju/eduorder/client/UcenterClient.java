package com.houyongju.eduorder.client;

import com.houyongju.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author HouYongJu
 * @create 2021-08-20 11:04
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    //根据用户id获取用户信息
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
