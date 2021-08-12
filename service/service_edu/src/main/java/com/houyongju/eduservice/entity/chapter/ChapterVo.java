package com.houyongju.eduservice.entity.chapter;

import lombok.Data;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-08-12 10:22
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoVo> children ;
}
