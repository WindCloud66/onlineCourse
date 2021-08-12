package com.houyongju.eduservice.controller;


import com.houyongju.commonutils.ResultMessage;
import com.houyongju.eduservice.entity.EduVideo;
import com.houyongju.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-11
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class  EduVideoController {
    @Autowired
    private EduVideoService videoService;
    //添加小节
    @PostMapping("addVideo")
    public ResultMessage addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);

        return ResultMessage.ok();
    }
    // TOOD 后面删除小节需要把里面的视频删除
    //删除小节
    @DeleteMapping("{id}")
    public ResultMessage deleteVideo(@PathVariable String id){
        videoService.removeById(id);
        return  ResultMessage.ok();
    }
    //修改小节
    @PostMapping("updateVideo")
    public ResultMessage updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return  ResultMessage.ok();
    }
    //获取章节
    @GetMapping("getVideoInfo/{videoId}")
    public  ResultMessage addVideo(@PathVariable String videoId){
        EduVideo eduVideo = videoService.getById(videoId);

        return ResultMessage.ok().data("video",eduVideo);
    }
}

