package com.houyongju.msmservice.service.Impl;

import com.houyongju.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author HouYongJu
 * @create 2021-08-16 17:45
 */
@Service
public class MsmServiceImpl implements MsmService {
    // TODO 手机发送验证码
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if(StringUtils.isEmpty(phone)){
            return false;
        }


        return true;
    }
}
