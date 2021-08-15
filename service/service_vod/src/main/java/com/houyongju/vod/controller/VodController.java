package com.houyongju.vod.controller;

import com.houyongju.commonutils.ResultMessage;
import com.houyongju.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-08-13 19:49
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;
    //上传视频到阿里云
    @PostMapping("uploadAliCloudVideo")
    public ResultMessage uploadAliCloudeVideo(MultipartFile file){
        String videoId = vodService.uploadCloudeVideo(file);
        return ResultMessage.ok().data("videoId", videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAliCloudVideo/{id}")
    public ResultMessage removeAliCloudVideo(@PathVariable String id){
        vodService.removeAliCloudVideo(id);

        return ResultMessage.ok();
    }

    @DeleteMapping("delete-video-batch")
    public ResultMessage deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeMoreAliCloudVideo(videoIdList);
        return ResultMessage.ok();
    }
}
