package com.houyongju.eduservice.controller;


import com.houyongju.commonutils.ResultMessage;
import com.houyongju.eduservice.entity.EduChapter;
import com.houyongju.eduservice.entity.chapter.ChapterVo;
import com.houyongju.eduservice.service.EduChapterService;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @GetMapping("getChapterVideo/{courseId}")
    public ResultMessage getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);

        return ResultMessage.ok().data("allChapterVideo", list);
    }
    //添加章节
    @PostMapping("addChapter")
    public  ResultMessage addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);

        return ResultMessage.ok();
    }
    //获取章节
    @GetMapping("getChapterInfo/{chapterId}")
    public  ResultMessage addChapter(@PathVariable String chapterId){
        EduChapter eduChapter = chapterService.getById(chapterId);

        return ResultMessage.ok().data("chapter",eduChapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public  ResultMessage updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);

        return ResultMessage.ok();
    }
    @DeleteMapping("{chapterId}")
    public ResultMessage deleteChapter(@PathVariable String chapterId){
        boolean flag = chapterService.deleteChapter(chapterId);
        if(flag){
            return ResultMessage.ok();
        }else{
            return ResultMessage.error();
        }


    }

}

