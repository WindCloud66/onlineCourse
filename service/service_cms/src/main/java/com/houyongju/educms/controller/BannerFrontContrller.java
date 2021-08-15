package com.houyongju.educms.controller;

import com.houyongju.commonutils.ResultMessage;
import com.houyongju.educms.entity.CrmBanner;
import com.houyongju.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-08-15 17:17
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontContrller {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getAllBanner")
    public ResultMessage findAllBanner(){

        List<CrmBanner> list =  crmBannerService.selectAllBanner();


        return ResultMessage.ok().data("list", list);
    }
}
