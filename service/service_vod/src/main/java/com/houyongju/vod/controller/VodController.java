package com.houyongju.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.houyongju.commonutils.ResultMessage;
import com.houyongju.servicebase.exceptionhandler.WindException;
import com.houyongju.vod.service.VodService;
import com.houyongju.vod.utils.ConstantVodUtils;
import com.houyongju.vod.utils.InitVodClient;
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

    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public ResultMessage getPlayAuth(@PathVariable String id){
        try {
            //初始化
            DefaultAcsClient client = InitVodClient.initVodClient(
                    ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.END_POINT);
            //请求
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);

            //响应
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            //得到播放凭证
            String playAuth = response.getPlayAuth();
            return ResultMessage.ok().data("playAuth", playAuth);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new WindException(20001, "获取凭证失败");
        }
    }
}
