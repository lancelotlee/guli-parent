package com.sorlin.eduservice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorlin.ResultCode;
import com.sorlin.eduservice.entity.EduCourse;
import com.sorlin.eduservice.entity.EduCourseDescription;
import com.sorlin.eduservice.entity.frontvo.CourseQueryVo;
import com.sorlin.eduservice.entity.frontvo.CourseWebVo;
import com.sorlin.eduservice.entity.vo.CourseInfoVo;
import com.sorlin.eduservice.entity.vo.CoursePublishVo;
import com.sorlin.eduservice.mapper.EduCourseMapper;
import com.sorlin.eduservice.service.EduChapterService;
import com.sorlin.eduservice.service.EduCourseDescriptionService;
import com.sorlin.eduservice.service.EduCourseService;
import com.sorlin.eduservice.service.EduVideoService;
import com.sorlin.eduservice.util.PageMapUtils;
import com.sorlin.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-03
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    private final EduCourseDescriptionService eduCourseDescriptionService;
    private final EduVideoService eduVideoService;
    private final EduChapterService eduChapterService;


    public EduCourseServiceImpl(EduCourseDescriptionService eduCourseDescriptionService, EduVideoService eduVideoService, EduChapterService eduChapterService) {
        this.eduCourseDescriptionService = eduCourseDescriptionService;
        this.eduVideoService = eduVideoService;
        this.eduChapterService = eduChapterService;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class}, noRollbackFor = {GuliException.class})
    public String addEduCourse(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);

        int insert = baseMapper.insert(eduCourse);
        if (insert < 0) {
            throw new GuliException(20001, "添加课程信息失败");
        }

        String cid = eduCourse.getId();

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(cid);
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.save(eduCourseDescription);

        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {

        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();

        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();

        BeanUtils.copyProperties(courseInfoVo, eduCourse);

        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new GuliException(200001, "修改失败");
        }

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        return baseMapper.getCoursePublishVo(courseId);
    }

    @Override
    public boolean publishCourse(String courseId) {
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus(EduCourse.COURSE_NORMAL);
        int count = baseMapper.updateById(course);
        return count > 0;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, EduCourse courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();

        if (courseQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();
        String teacherId = courseQuery.getTeacherId();
        String title = courseQuery.getTitle();

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("subject_id", subjectId);
        }

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeCourse(String courseId) {
        eduVideoService.removeVideoByCourseId(courseId);

        eduChapterService.removeChapterByCourseId(courseId);

        eduCourseDescriptionService.removeById(courseId);

        int result = baseMapper.deleteById(courseId);

        if (result == 0) {
            throw new GuliException(ResultCode.ERROR.getCode(), "删除失败");
        }
    }

    @Override
    @Cacheable(value = "course", key = "'index'")
    public List<EduCourse> getIndex() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<EduCourse> selectByTeacherId(String id) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<EduCourse>();

        queryWrapper.eq("teacher_id", id);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");

        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseQueryVo courseQueryVo) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(courseQueryVo.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseQueryVo.getSubjectParentId());
        }

        if (StrUtil.isNotEmpty(courseQueryVo.getSubjectId())) {
            queryWrapper.eq("subject_id", courseQueryVo.getSubjectId());
        }

        if (StrUtil.isNotEmpty(courseQueryVo.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (StrUtil.isNotEmpty(courseQueryVo.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }

        if (StrUtil.isNotEmpty(courseQueryVo.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam, queryWrapper);


        return PageMapUtils.pageToMap(pageParam);
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String id) {
        return baseMapper.getCourseWebVo(id);
    }
}
