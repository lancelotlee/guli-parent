package com.sorlin.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sorlin.eduservice.entity.EduCourse;
import com.sorlin.eduservice.entity.frontvo.CourseWebVo;
import com.sorlin.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author sorlin
 * @since 2020-08-03
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getCoursePublishVo(String courseId);

    CourseWebVo getCourseWebVo(String courseId);

}
