package com.houyongju.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houyongju.commonutils.ResultMessage;
import com.houyongju.eduservice.entity.EduTeacher;
import com.houyongju.eduservice.entity.vo.TeacherQuery;
import com.houyongju.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-06
 */
@RestController
@RequestMapping("/eduservice/teacher")

public class EduTeacherController {
    //将service进行注入
    @Autowired
    private EduTeacherService teacherService;
    // 查询所有讲师表所有数据
    @GetMapping("findAll")
    public ResultMessage findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
//        try {
//            int i = 10 / 0;
//        }catch (Exception e){
//            throw new WindException(20001,"执行了自定义异常处理");
//        }

        return ResultMessage.ok().data("items", list);
    }
    //删除讲师
    @DeleteMapping("{id}")
    public ResultMessage removeTeacher(@PathVariable String  id){
        boolean flag = teacherService.removeById(id);
        if(flag){
            return ResultMessage.ok();
        }
        return ResultMessage.error();
    }
    @GetMapping("pageTeacher/{current}/{limit}")
    public ResultMessage pageListTeacher(@PathVariable long current,
                                          @PathVariable long limit){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //调用方法时候,底层封装,把分页所有数据封装到pageTeacher对象里面
        teacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();


        return ResultMessage.ok().data("total", total).data("rows", records);
    }

    //讲师的分页操作
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public ResultMessage pageTeacherCondition(@PathVariable long current,
                                              @PathVariable long limit,
                                              @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }

        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level", level);
        }

        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create", begin);
        }

        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create", end);
        }
//        System.out.println(begin);
//        System.out.println(end);

        wrapper.orderByDesc("gmt_create");

        teacherService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();


        return ResultMessage.ok().data("total", total).data("rows", records);


 }
    // 添加讲师的方法
    @PostMapping("addTeacher")
    public ResultMessage addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if(save){
            return ResultMessage.ok();
        }else{
            return ResultMessage.error();
        }

    }
    @GetMapping("getTeacher/{id}")
    public ResultMessage getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return ResultMessage.ok().data("teacher", eduTeacher);
    }
    @PostMapping("updateTeacher")
    public  ResultMessage updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag){
            return ResultMessage.ok();
        }else{
            return ResultMessage.error();
        }

    }

}

