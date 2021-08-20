package com.houyongju.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houyongju.commonutils.ResultMessage;
import com.houyongju.eduservice.entity.EduCourse;
import com.houyongju.eduservice.entity.chapter.ChapterVo;
import com.houyongju.eduservice.entity.frontvo.CourseFrontVo;
import com.houyongju.eduservice.entity.frontvo.CourseWebVo;
import com.houyongju.eduservice.service.EduChapterService;
import com.houyongju.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author HouYongJu
 * @create 2021-08-18 14:34
 */
@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    @PostMapping(value = "getFrontCourseList/{page}/{limit}")
    public ResultMessage getFrontCourseList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.pageCourseFrontList(pageCourse, courseFrontVo);
        return ResultMessage.ok().data(map);
    }

    @GetMapping("getFrontCourseInfo/{courseId}")
    public ResultMessage getFrontCourseInfo(@PathVariable String courseId){
        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);


        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);


        return ResultMessage.ok().data("chapterVoList", chapterVoList).data("courseWebVo", courseWebVo);
    }

}
