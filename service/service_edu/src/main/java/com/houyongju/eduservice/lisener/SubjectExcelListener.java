package com.houyongju.eduservice.lisener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.houyongju.eduservice.entity.excel.SubjectData;

/**
 * @author HouYongJu
 * @create 2021-08-10 23:47
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
