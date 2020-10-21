package com.sorlin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorlin.eduservice.entity.EduTeacher;
import com.sorlin.eduservice.entity.vo.EduTeacherQuery;
import com.sorlin.eduservice.mapper.EduTeacherMapper;
import com.sorlin.eduservice.service.EduTeacherService;
import com.sorlin.eduservice.util.PageMapUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author sorlin
 * @since 2020-07-23
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, EduTeacherQuery eduTeacherQuery) {

        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (eduTeacherQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String name = eduTeacherQuery.getName();
        Integer level = eduTeacherQuery.getLevel();
        String begin = eduTeacherQuery.getBegin();
        String end = eduTeacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    @Cacheable(value = "teacher", key = "'index'")
    public List<EduTeacher> getIndex() {
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        return baseMapper.selectList(wrapperTeacher);
    }

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageParam) {
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByAsc("sort");
        baseMapper.selectPage(pageParam, teacherQueryWrapper);
        return PageMapUtils.pageToMap(pageParam);
    }
}
