package com.sorlin.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sorlin.eduservice.entity.EduSubject;
import com.sorlin.eduservice.entity.vo.EduSubjectOneVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author sorlin
 * @since 2020-07-31
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile multipartFile,EduSubjectService eduSubjectService);

    List<EduSubjectOneVo> getAllSubject();
}
