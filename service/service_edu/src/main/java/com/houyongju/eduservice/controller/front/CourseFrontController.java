package com.houyongju.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houyongju.commonutils.JwtUtils;
import com.houyongju.commonutils.ResultMessage;
import com.houyongju.commonutils.ordervo.CourseWebVoOrder;
import com.houyongju.eduservice.client.OrdersClient;
import com.houyongju.eduservice.entity.EduCourse;
import com.houyongju.eduservice.entity.chapter.ChapterVo;
import com.houyongju.eduservice.entity.frontvo.CourseFrontVo;
import com.houyongju.eduservice.entity.frontvo.CourseWebVo;
import com.houyongju.eduservice.service.EduChapterService;
import com.houyongju.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private OrdersClient ordersClient;

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
    //TODO 用户id是否已经登录
    @GetMapping("getFrontCourseInfo/{courseId}")
    public ResultMessage getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);


        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);

        //根据课程id 和用户id查询课程是否已经查询过
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        boolean buyCourse = ordersClient.isBuyCourse(courseId, memberIdByJwtToken);


        return ResultMessage.ok()
                .data("chapterVoList", chapterVoList)
                .data("courseWebVo", courseWebVo)
                .data("isBuy", buyCourse);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo, courseWebVoOrder);
        return courseWebVoOrder;
    }
}
