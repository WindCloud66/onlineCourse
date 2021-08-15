package com.houyongju.eduservice.controller.front;

import com.houyongju.commonutils.ResultMessage;
import com.houyongju.eduservice.service.IndexFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HouYongJu
 * @create 2021-08-15 20:03
 */
@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private IndexFrontService indexFrontService;
    //查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    public ResultMessage index() {
        ResultMessage message = indexFrontService.index();

        return message;
    }
}
