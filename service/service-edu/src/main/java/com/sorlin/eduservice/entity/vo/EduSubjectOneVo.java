package com.sorlin.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: guli-parent
 * @description: 课程分类一级节点VO
 * @author: sorlin
 * @create: 2020-08-02 11:04
 */
@Data
public class EduSubjectOneVo {
    private String id;
    private String title;
    private List<EduSubjectVo> children = new ArrayList<>();
}
