package com.houyongju.eduservice.client;

import com.houyongju.commonutils.ResultMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-08-14 16:35
 */
@Component
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
public interface VocClient {


    //根据id删除阿里云视频
    @DeleteMapping("/eduvod/video/removeAliCloudVideo/{id}")
    public ResultMessage removeAliCloudVideo(@PathVariable("id") String id);

    //根据ids删除多个阿里云视频
    @DeleteMapping("/eduvod/video/delete-video-batch")
    public ResultMessage deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
