package com.sorlin.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sorlin.Result;
import com.sorlin.eduservice.entity.EduCourse;
import com.sorlin.eduservice.entity.frontvo.CourseWebVo;
import com.sorlin.eduservice.entity.vo.CourseInfoVo;
import com.sorlin.eduservice.entity.vo.CoursePublishVo;
import com.sorlin.eduservice.service.EduCourseService;
import com.sorlin.vo.CourseWebInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author sorlin
 * @since 2020-08-03
 */
@Api(tags = {"课程管理"})
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {
    private final EduCourseService eduCourseService;

    public EduCourseController(EduCourseService eduCourseService) {
        this.eduCourseService = eduCourseService;
    }
    private static final String NORMAL = "Normal";


    @ApiOperation(value = "分页课程列表")
    @PostMapping("getCourseList/{page}/{limit}")
    public Result getCourseList(

            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit, @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody(required = false) EduCourse courseQuery) {

        Page<EduCourse> pageParam = new Page<>(page, limit);

        eduCourseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return Result.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增课程")
    @PostMapping("addEduCourseInfo")
    public Result addEduCourseInfo(@ApiParam(name = "CourseInfoVo", value = "课程基本信息", required = true)
                                   @RequestBody CourseInfoVo courseInfoVo) {
        String cid = eduCourseService.addEduCourse(courseInfoVo);
        return Result.ok().data("courseId", cid);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfoVo", courseInfoVo);

    }

    @PutMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return Result.ok();
    }
    @GetMapping("getPublishCourseInfo/{courseId}")
    public Result getPublishCourseInfo(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = eduCourseService.getPublishCourseInfo(courseId);
        return  Result.ok().data("data",coursePublishVo);
    }


    @PutMapping("publishCourse/{courseId}")
    public Result publishCourse(@PathVariable String courseId){

        boolean count  = eduCourseService.publishCourse(courseId);
        return  Result.ok();
    }

    @DeleteMapping("deleteCourse/{courseId}")
    public Result deleteCourse(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);
        return Result.ok();
    }

    //根据课程id查询课程信息
    @GetMapping("getCourseWebInfo/{courseId}")
    public CourseWebInfo getCourseWebInfo(@PathVariable String courseId) {
        CourseWebVo baseCourseInfo = eduCourseService.getBaseCourseInfo(courseId);
        CourseWebInfo courseWebInfo = new CourseWebInfo();
        BeanUtils.copyProperties(baseCourseInfo,courseWebInfo);
        return courseWebInfo;
    }






}

