package com.houyongju.msmservice.service;

import java.util.Map;

/**
 * @author HouYongJu
 * @create 2021-08-16 17:44
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
