package com.sorlin.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: guli-parent
 * @description: 课程远程调用Vo
 * @author: sorlin
 * @create: 2020-08-27 14:00
 */
@Data
public class CourseWebInfo  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private String description;

    private String teacherId;

    private String teacherName;

    private String intro;

    private String avatar;

    private String subjectLevelOneId;

    private String subjectLevelOne;

    private String subjectLevelTwoId;

    private String subjectLevelTwo;
}