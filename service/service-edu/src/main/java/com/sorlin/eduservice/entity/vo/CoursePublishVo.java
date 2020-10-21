package com.sorlin.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: guli-parent
 * @description: 课程发布信息
 * @author: sorlin
 * @create: 2020-08-06 19:53
 */
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}