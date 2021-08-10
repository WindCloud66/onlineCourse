package com.houyongju.oss.controller;

import com.houyongju.commonutils.ResultMessage;
import com.houyongju.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author HouYongJu
 * @create 2021-08-10 14:26
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    @PostMapping
    public ResultMessage updateOssFile(MultipartFile file){
        //返回上传到oss的路径
        String url = ossService.uploadFileAvator(file);

        return ResultMessage.ok().data("url", url);
    }
}
