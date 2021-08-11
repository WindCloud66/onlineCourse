package com.houyongju.eduservice.controller;


import com.houyongju.commonutils.ResultMessage;
import com.houyongju.eduservice.entity.subject.OneSubject;
import com.houyongju.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-10
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public ResultMessage addSubject(MultipartFile file){
        if(file == null){
            return ResultMessage.error();
        }
        eduSubjectService.saveSubject(file,eduSubjectService);
        return ResultMessage.ok();
    }


    @GetMapping("getAllSubject")
    public ResultMessage getAllSubject(){
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return ResultMessage.ok().data("list",list);
    }
}

