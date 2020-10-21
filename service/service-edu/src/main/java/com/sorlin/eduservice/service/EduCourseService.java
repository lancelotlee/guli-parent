package com.sorlin.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sorlin.eduservice.entity.EduCourse;
import com.sorlin.eduservice.entity.frontvo.CourseQueryVo;
import com.sorlin.eduservice.entity.frontvo.CourseWebVo;
import com.sorlin.eduservice.entity.vo.CourseInfoVo;
import com.sorlin.eduservice.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-03
 */
public interface EduCourseService extends IService<EduCourse> {

    String addEduCourse(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String courseId);

    boolean publishCourse(String courseId);

    void pageQuery(Page<EduCourse> pageParam, EduCourse courseQuery);

    void removeCourse(String courseId);

    List<EduCourse> getIndex();

    List<EduCourse> selectByTeacherId(String id);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseQueryVo courseQueryVo);

    CourseWebVo getBaseCourseInfo(String id);
}
