package com.houyongju.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-08-11 14:11
 */
@Data
public class TwoSubject {
    private String id;
    private String title;

    private List<TwoSubject> children = new ArrayList<>();
}
