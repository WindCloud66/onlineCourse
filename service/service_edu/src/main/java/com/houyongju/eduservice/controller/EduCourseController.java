package com.houyongju.eduservice.controller;


import com.houyongju.commonutils.ResultMessage;
import com.houyongju.eduservice.entity.vo.CourseInfoVo;
import com.houyongju.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-11
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;
    //添加课程基本信息
    @PostMapping("addCourseInfo")
    public ResultMessage addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return ResultMessage.ok().data("courseId", id);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public ResultMessage getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);

        return ResultMessage.ok().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public ResultMessage updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        String courseId = courseInfoVo.getId();
        return ResultMessage.ok().data("courseId", courseId);
    }
}

