package com.houyongju.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houyongju.commonutils.ResultMessage;
import com.houyongju.eduservice.entity.EduCourse;
import com.houyongju.eduservice.entity.EduTeacher;
import com.houyongju.eduservice.service.EduCourseService;
import com.houyongju.eduservice.service.EduTeacherService;
import com.houyongju.eduservice.service.IndexFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-08-15 20:17
 */
@Service
public class IndexFrontServiceImpl implements IndexFrontService {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;
    @Override
    public ResultMessage index() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduList = courseService.list(wrapper);
        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return  ResultMessage.ok().data("eduList",eduList).data("teacherList",teacherList);
    }
}
