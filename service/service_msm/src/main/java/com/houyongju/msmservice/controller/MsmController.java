package com.houyongju.msmservice.controller;

import com.houyongju.commonutils.ResultMessage;
import com.houyongju.msmservice.service.MsmService;
import com.houyongju.msmservice.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author HouYongJu
 * @create 2021-08-16 17:43
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
@Slf4j
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("send/{phone}")
    public ResultMessage sendMsm(@PathVariable String phone){

        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return ResultMessage.ok();
        }

         code = RandomUtil.getFourBitRandom();
        log.info(code);
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = msmService.send(param, phone);
        if(isSend){

            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

            return ResultMessage.ok().data("code", code);
        }
        return ResultMessage.error().message("短信发送失败");

    }
}
