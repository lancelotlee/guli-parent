package com.sorlin.eduservice.controller;


import com.sorlin.Result;
import com.sorlin.eduservice.entity.vo.EduSubjectOneVo;
import com.sorlin.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author sorlin
 * @since 2020-07-31
 */
@Api(tags = {"课程列表管理"})
@RestController
@RequestMapping("/eduservice/edu-subject")
public class EduSubjectController {
    private final EduSubjectService eduSubjectService;

    public EduSubjectController(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @ApiOperation(value = "添加课程分类")
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return Result.ok();

    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("getAllSubject")
    public Result nestedList() {

        List<EduSubjectOneVo> eduSubjectOneVos = eduSubjectService.getAllSubject();
        return Result.ok().data("items", eduSubjectOneVos);
    }

}

