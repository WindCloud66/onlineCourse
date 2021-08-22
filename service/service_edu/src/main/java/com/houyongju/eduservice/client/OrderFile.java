package com.houyongju.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @author HouYongJu
 * @create 2021-08-21 16:35
 */
@Component
public class OrderFile implements OrdersClient{
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
