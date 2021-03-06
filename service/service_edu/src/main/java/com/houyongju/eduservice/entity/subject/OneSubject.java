package com.houyongju.eduservice.entity.subject;

import lombok.Data;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-08-11 14:03
 */
@Data
public class OneSubject {

    private String id;
    private String title;

    private List<TwoSubject> children;
}
