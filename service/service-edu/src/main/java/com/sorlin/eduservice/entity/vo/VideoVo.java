package com.sorlin.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: guli-parent
 * @description: VideoVo
 * @author: sorlin
 * @create: 2020-08-04 15:20
 */
@ApiModel(value = "课时信息")
@Data
public class VideoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String videoSourceId;
    private Boolean isFree;
}
