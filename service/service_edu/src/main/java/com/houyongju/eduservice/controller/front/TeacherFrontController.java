package com.houyongju.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houyongju.commonutils.ResultMessage;
import com.houyongju.eduservice.entity.EduCourse;
import com.houyongju.eduservice.entity.EduTeacher;
import com.houyongju.eduservice.service.EduCourseService;
import com.houyongju.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author HouYongJu
 * @create 2021-08-18 14:34
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;
    //分页查询讲师
    @PostMapping("getTeacherFront/{page}/{limit}")
    public ResultMessage getTeacherFront(@PathVariable long page, @PathVariable long limit){
        Page<EduTeacher> teacherPage = new Page<EduTeacher>(page, limit);
        Map<String, Object> map = teacherService.getTeacherFrontList(teacherPage);
        return ResultMessage.ok().data(map);
    }

    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public ResultMessage getTeacherFrontInfo(@PathVariable String teacherId){
        // 根据讲师id查询讲师基本信息
        EduTeacher teacher = teacherService.getById(teacherId);

        // 2 根据讲师id查询所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<EduCourse> list = courseService.list(wrapper);



        return ResultMessage.ok().data("teacher", teacher).data("courseList", list);
    }

}
