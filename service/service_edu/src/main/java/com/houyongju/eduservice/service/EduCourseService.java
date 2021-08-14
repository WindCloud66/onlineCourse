package com.houyongju.eduservice.service;

import com.houyongju.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.houyongju.eduservice.entity.vo.CourseInfoVo;
import com.houyongju.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-08-11
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publichCourseInfo(String id);

    void publichCourseById(String id);

    void removeCourse(String courseId);
}
