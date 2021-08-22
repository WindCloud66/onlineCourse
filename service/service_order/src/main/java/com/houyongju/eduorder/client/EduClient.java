package com.houyongju.eduorder.client;

import com.houyongju.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author HouYongJu
 * @create 2021-08-20 11:03
 */
@Component
@FeignClient("service-edu")
public interface EduClient {
    //根据课程id查询课程信息
    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);
}
