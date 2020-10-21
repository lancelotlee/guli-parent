package com.sorlin.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sorlin.Result;
import com.sorlin.eduservice.entity.EduTeacher;
import com.sorlin.eduservice.entity.vo.EduTeacherQuery;
import com.sorlin.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author sorlin
 * @since 2020-07-23
 */
@Api(tags = {"讲师管理"})
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * @return java.util.List<com.sorlin.eduservice.entity.EduTeacher>
     * @Author sorlin
     * @Description //所有讲师列表
     * @Date 16:52 2020/7/23
     * @Param []
     **/
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public Result findAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("items", list);

    }

    /**
     * @return com.sorlin.Result
     * @Author sorlin
     * @Description //根据ID删除讲师
     * @Date 11:27 2020/7/24
     * @Param [id]
     **/
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("/remove/{id}")
    public Result removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    /**
     * @return com.sorlin.Result
     * @Author sorlin
     * @Description //分页讲师列表
     * @Date 11:26 2020/7/24
     * @Param [page, limit]
     **/
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public Result pageList(

            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        eduTeacherService.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return Result.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageQuery(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody(required = false) EduTeacherQuery teacherQuery) {

        Page<EduTeacher> pageParam = new Page<>(current, limit);

        eduTeacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return Result.ok().data("total", total).data("rows", records);
    }


    @ApiOperation(value = "新增讲师")
    @PostMapping
    public Result save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        eduTeacherService.save(teacher);
        return Result.ok();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public Result getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {

        EduTeacher teacher = eduTeacherService.getById(id);
        return Result.ok().data("teacher", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping
    public Result updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher) {
        eduTeacherService.updateById(eduTeacher);
        return Result.ok();
    }

}

