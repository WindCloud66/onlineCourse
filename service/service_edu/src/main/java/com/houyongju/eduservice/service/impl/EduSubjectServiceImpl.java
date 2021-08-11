package com.houyongju.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.houyongju.eduservice.entity.EduSubject;
import com.houyongju.eduservice.entity.excel.SubjectData;
import com.houyongju.eduservice.entity.subject.OneSubject;
import com.houyongju.eduservice.entity.subject.TwoSubject;
import com.houyongju.eduservice.lisener.SubjectExcelListener;
import com.houyongju.eduservice.mapper.EduSubjectMapper;
import com.houyongju.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-08-10
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {

        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,
                    new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        // 查询所有一级分类 parent id = 0;
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneEduSubjects = baseMapper.selectList(wrapperOne);

        // 查询所有二级分类 parent id = 0;
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoEduSubjects = baseMapper.selectList(wrapperTwo);



        List<OneSubject> finalSubjectList = new ArrayList<>();
        for(EduSubject oneEduSubject: oneEduSubjects) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(oneEduSubject, oneSubject);

            finalSubjectList.add(oneSubject);


            List<TwoSubject> twoFinalSubject = new ArrayList<>();
            for(EduSubject twoEduSubject : twoEduSubjects){
                if(twoEduSubject.getParentId().equals(oneEduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoEduSubject, twoSubject);
                    twoFinalSubject.add(twoSubject);
                }
            }

            oneSubject.setChildren(twoFinalSubject);
        }
        return finalSubjectList ;
    }
}
