package com.houyongju.eduservice.controller;

import com.houyongju.commonutils.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author HouYongJu
 * @create 2021-08-09 10:21
 */
@Slf4j
@RestController
@RequestMapping("/eduservice/user")

public class EduLoginController {
    @PostMapping("login")
    public ResultMessage login(){
        log.info("login");
        return ResultMessage.ok().data("token", "admin");
    }

    @GetMapping("info")
    public ResultMessage info(){
        log.info("info");
        return ResultMessage.ok().data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://pic.qqtn.com/up/2019-9/15690311636958128.jpg");
    }
}
