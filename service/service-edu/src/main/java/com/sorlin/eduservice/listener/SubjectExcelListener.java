package com.sorlin.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sorlin.eduservice.entity.EduSubject;
import com.sorlin.eduservice.entity.excel.ExcelSubjectData;
import com.sorlin.eduservice.service.EduSubjectService;
import com.sorlin.servicebase.exceptionhandler.GuliException;

/**
 * @program: guli-parent
 * @description: 读取Excel监听器
 * @author: sorlin
 * @create: 2020-08-01 08:56
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    private final EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }


    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if(excelSubjectData == null) {
            throw new GuliException(20001,"添加失败");
        }
        //添加一级分类
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService,excelSubjectData.getOneSubjectName());
        //没有相同的
        if(existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(excelSubjectData.getOneSubjectName());
            existOneSubject.setParentId("0");
            eduSubjectService.save(existOneSubject);
        }

        //获取一级分类id值
        String pid = existOneSubject.getId();

        //添加二级分类
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService,excelSubjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(excelSubjectData.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            eduSubjectService.save(existTwoSubject);
        }

    }

    /**
     * @Author sorlin
     * @Description //判断一级分类是否存在
     * @Date 9:32 2020/8/1
     * @Param [subjectService, name]
     * @return com.sorlin.eduservice.entity.EduSubject
     **/
    private EduSubject existOneSubject(EduSubjectService subjectService,String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        return subjectService.getOne(wrapper);
    }
    /**
     * @Author sorlin
     * @Description //判断二级分类是否存在
     * @Date 9:32 2020/8/1
     * @Param [subjectService, name, pid]
     * @return com.sorlin.eduservice.entity.EduSubject
     **/
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        return subjectService.getOne(wrapper);
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
