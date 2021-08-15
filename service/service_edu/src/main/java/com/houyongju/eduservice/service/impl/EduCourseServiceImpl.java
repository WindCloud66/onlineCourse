package com.houyongju.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.houyongju.eduservice.entity.EduCourse;
import com.houyongju.eduservice.entity.EduCourseDescription;
import com.houyongju.eduservice.entity.vo.CourseInfoVo;
import com.houyongju.eduservice.entity.vo.CoursePublishVo;
import com.houyongju.eduservice.mapper.EduCourseMapper;
import com.houyongju.eduservice.service.EduChapterService;
import com.houyongju.eduservice.service.EduCourseDescriptionService;
import com.houyongju.eduservice.service.EduCourseService;
import com.houyongju.eduservice.service.EduVideoService;
import com.houyongju.servicebase.exceptionhandler.WindException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-08-11
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    // 小节注入
    @Autowired
    private EduVideoService eduVideoService;
    // 章节注入
    @Autowired
    private EduChapterService eduChapterService;
    //添加课程基本信息
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);

        if(insert <= 0){
            throw  new WindException(20001,"添加课程失败");
        }
        String id = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(id);
        eduCourseDescription.setDescription(courseInfoVo.getDescription());

        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
//        System.out.println(save);

        return id;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        // 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);

        //查询描述表
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        BeanUtils.copyProperties(courseDescription, courseInfoVo);

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        // 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update <= 0){
            throw  new WindException(20001,"添加课程失败");
        }

        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(description);


    }

    @Override
    public CoursePublishVo publichCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void publichCourseById(String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        baseMapper.updateById(eduCourse);
    }
    // TOOD 应该使用事务去删除
    //  课程删除应该是小节删除
    @Override
    public void removeCourse(String courseId) {
        //1 根据课程id 删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        //2 根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);
        //3 根据课程id删除描述
        eduCourseDescriptionService.removeById(courseId);
        //4 根据课程id删除课程
        baseMapper.deleteById(courseId);
    }
}
