package com.sorlin.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sorlin.Result;
import com.sorlin.eduservice.entity.EduCourse;
import com.sorlin.eduservice.entity.EduTeacher;
import com.sorlin.eduservice.service.EduCourseService;
import com.sorlin.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: guli-parent
 * @description: 讲师前台显示
 * @author: sorlin
 * @create: 2020-08-25 14:05
 */
@Api(tags = {"讲师前台显示"})
@RestController
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {

    private final EduTeacherService eduTeacherService;

    private final EduCourseService eduCourseService;

    public TeacherFrontController(EduTeacherService eduTeacherService, EduCourseService eduCourseService) {
        this.eduTeacherService = eduTeacherService;
        this.eduCourseService = eduCourseService;
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("getTeacherFrontList/{page}/{limit}")
    public Result getTeacherFrontList(@ApiParam(name = "page", value = "当前页码", required = true)
                                      @PathVariable Long page,

                                      @ApiParam(name = "limit", value = "每页记录数", required = true)
                                      @PathVariable Long limit) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);

        Map<String, Object> map = eduTeacherService.getTeacherFrontList(pageParam);

        return Result.ok().data(map);

    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "{id}")
    public Result getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {

        //查询讲师信息
        EduTeacher teacher = eduTeacherService.getById(id);

        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = eduCourseService.selectByTeacherId(id);

        return Result.ok().data("teacher", teacher).data("courseList", courseList);
    }

}
