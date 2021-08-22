package com.houyongju.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author HouYongJu
 * @create 2021-08-21 15:08
 */
@Component
@FeignClient( value="service-order", fallback = OrderFile.class)
public interface OrdersClient {
    @GetMapping("/eduorder/order/isBuyCourese/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId,
                               @PathVariable("memberId") String memberId);
}
