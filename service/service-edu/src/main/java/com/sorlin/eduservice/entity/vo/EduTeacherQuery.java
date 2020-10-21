package com.sorlin.eduservice.entity.vo;/**
 * @author 李松岭
 * @date 2020-07-24 15:57
 **/

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *@program: guli-parent
 *@description: 讲师查询对象封装
 *@author: sorlin
 *@create: 2020-07-24 15:57
 */
@ApiModel(value = "Teacher查询对象", description = "讲师查询对象封装")
@Data
public class EduTeacherQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    //注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}