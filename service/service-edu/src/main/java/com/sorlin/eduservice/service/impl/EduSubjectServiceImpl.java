package com.sorlin.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorlin.eduservice.entity.EduSubject;
import com.sorlin.eduservice.entity.excel.ExcelSubjectData;
import com.sorlin.eduservice.entity.vo.EduSubjectOneVo;
import com.sorlin.eduservice.entity.vo.EduSubjectVo;
import com.sorlin.eduservice.listener.SubjectExcelListener;
import com.sorlin.eduservice.mapper.EduSubjectMapper;
import com.sorlin.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author sorlin
 * @since 2020-07-31
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile multipartFile, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<EduSubjectOneVo> getAllSubject() {
        ArrayList<EduSubjectOneVo> eduSubjectOneVos = new ArrayList<>();

        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryOneSubjects = new QueryWrapper<>();
        queryOneSubjects.eq("parent_id", 0);
        queryOneSubjects.orderByAsc("sort", "id");
        List<EduSubject> oneSubjects = baseMapper.selectList(queryOneSubjects);

        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryTwoSubjects = new QueryWrapper<>();
        queryTwoSubjects.ne("parent_id", 0);
        queryTwoSubjects.orderByAsc("sort", "id");
        List<EduSubject> twoSubjects = baseMapper.selectList(queryTwoSubjects);

        for (EduSubject oneSubject : oneSubjects) {
            EduSubjectOneVo eduSubjectOneVo = new EduSubjectOneVo();
            BeanUtils.copyProperties(oneSubject,eduSubjectOneVo);

            List<EduSubjectVo> objects = new ArrayList<>();
            for (EduSubject twoSubject : twoSubjects) {
                if (oneSubject.getId().equals(twoSubject.getParentId())){
                    EduSubjectVo eduSubjectVo = new EduSubjectVo();
                    BeanUtils.copyProperties(twoSubject,eduSubjectVo);
                    objects.add(eduSubjectVo);
                }
            }
            eduSubjectOneVo.setChildren(objects);
            eduSubjectOneVos.add(eduSubjectOneVo);
        }

        return eduSubjectOneVos;
    }
}
