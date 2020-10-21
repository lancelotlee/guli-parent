package com.sorlin.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: guli-parent
 * @description: 章节信息
 * @author: sorlin
 * @create: 2020-08-04 15:20
 */
@ApiModel(value = "章节信息")
@Data
public class ChapterVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
