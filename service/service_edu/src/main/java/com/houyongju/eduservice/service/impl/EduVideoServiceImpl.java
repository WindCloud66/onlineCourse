package com.houyongju.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houyongju.eduservice.client.VocClient;
import com.houyongju.eduservice.entity.EduVideo;
import com.houyongju.eduservice.mapper.EduVideoMapper;
import com.houyongju.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-08-11
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    private VocClient vocClient;
    //根据课程id 删除小节
    // TDDO 删除小节,删除对应视频文件
    @Override
    public void removeVideoByCourseId(String courseId) {
        //删除小节,删除对应视频文件
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);

        List<String> videoIdsList = new ArrayList<>();
        for(EduVideo video : eduVideoList){
            String videoSourceId = video.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                videoIdsList.add(videoSourceId);
            }
        }

        if(videoIdsList.size() > 0){
            vocClient.deleteBatch(videoIdsList);
        }



        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
