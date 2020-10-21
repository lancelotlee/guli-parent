package com.sorlin.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sorlin.eduservice.entity.EduTeacher;
import com.sorlin.eduservice.entity.vo.EduTeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author sorlin
 * @since 2020-07-23
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, EduTeacherQuery eduTeacherQuery);

    List<EduTeacher> getIndex();

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageParam);
}
