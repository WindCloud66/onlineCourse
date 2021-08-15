package com.houyongju.eduservice.controller;


import com.houyongju.commonutils.ResultMessage;
import com.houyongju.eduservice.entity.EduCourse;
import com.houyongju.eduservice.entity.vo.CourseInfoVo;
import com.houyongju.eduservice.entity.vo.CoursePublishVo;
import com.houyongju.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    //课程列表 基本实现
    // TOOD 完善条件查询带分页

    @GetMapping
    public ResultMessage getCourseList(){
        List<EduCourse> list = eduCourseService.list(null);
        return ResultMessage.ok().data("list", list);
    }



    //添加课程基本信息
    @PostMapping("addCourseInfo")
    public ResultMessage addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return ResultMessage.ok().data("courseId", id);
    }

    //获取课程信息
    @GetMapping("getCourseInfo/{courseId}")
    public ResultMessage getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);

        return ResultMessage.ok().data("courseInfoVo",courseInfoVo);
    }
    //更新课程信息
    @PostMapping("updateCourseInfo")
    public ResultMessage updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        String courseId = courseInfoVo.getId();
        return ResultMessage.ok().data("courseId", courseId);
    }

    //获取课程发布信息
    @GetMapping("getPublishCourseInfo/{id}")
    public ResultMessage getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = eduCourseService.publichCourseInfo(id);

        return ResultMessage.ok().data("coursePublish", coursePublishVo);
    }

    @ApiOperation(value = "根据id发布课程")
    @PostMapping("publishCourse/{id}")
    public ResultMessage publishCourseById(@PathVariable String id){


        eduCourseService.publichCourseById(id);

        return ResultMessage.ok();
    }

    //删除课程
    // TODO 删除还有bug video表中没有完全删除
    @DeleteMapping("{courseId}")
    public ResultMessage deleteCourse(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);

        return ResultMessage.ok();
    }


}

