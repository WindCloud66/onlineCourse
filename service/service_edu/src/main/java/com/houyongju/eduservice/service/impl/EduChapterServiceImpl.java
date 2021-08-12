package com.houyongju.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.houyongju.eduservice.entity.EduChapter;
import com.houyongju.eduservice.entity.EduVideo;
import com.houyongju.eduservice.entity.chapter.ChapterVo;
import com.houyongju.eduservice.entity.chapter.VideoVo;
import com.houyongju.eduservice.mapper.EduChapterMapper;
import com.houyongju.eduservice.service.EduChapterService;
import com.houyongju.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-08-11
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList((wrapperChapter));

        //2 根据课程id查询课程里面所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(wrapperVideo);

        //创建list集合,用于封装最终数据
        List<ChapterVo> finalList = new ArrayList<>();

        //3 遍历查询章节list集合进行封装
         for(int i = 0; i < eduChapterList.size(); i++){
             EduChapter eduChapter = eduChapterList.get(i);

             ChapterVo chapterVo = new ChapterVo();
             BeanUtils.copyProperties(eduChapter, chapterVo);

             finalList.add(chapterVo);

             List<VideoVo> videoVoList = new ArrayList<>();
             for(int k = 0; k < eduVideoList.size(); k++){
                 EduVideo eduVideo = eduVideoList.get(k);
                 if(eduVideo.getChapterId().equals(eduChapter.getId())){
                     VideoVo videoVo = new VideoVo();
                     BeanUtils.copyProperties(eduVideo, videoVo);
                     videoVoList.add(videoVo);
                 }

             }
             chapterVo.setChildren(videoVoList);
         }

     return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //查询小节表 如果有数据则不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", wrapper);
        int count = eduVideoService.count(wrapper);
        if(count > 0){
            return false;
        }else{
            int result = baseMapper.deleteById(chapterId);
            return result >0;
        }

    }
}
