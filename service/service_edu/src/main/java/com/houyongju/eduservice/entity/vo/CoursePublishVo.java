package com.houyongju.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author HouYongJu
 * @create 2021-08-12 21:38
 */
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
