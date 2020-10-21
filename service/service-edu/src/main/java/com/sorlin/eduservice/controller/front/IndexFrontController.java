package com.sorlin.eduservice.controller.front;

import com.sorlin.Result;
import com.sorlin.eduservice.entity.EduCourse;
import com.sorlin.eduservice.entity.EduTeacher;
import com.sorlin.eduservice.service.EduCourseService;
import com.sorlin.eduservice.service.EduTeacherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: guli-parent
 * @description: 前端查询课程名师接口
 * @author: sorlin
 * @create: 2020-08-17 20:14
 */
@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    private final EduCourseService courseService;
    private final EduTeacherService teacherService;

    public IndexFrontController(EduCourseService courseService, EduTeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping("index")
    public Result index() {
        //查询前8条热门课程
        List<EduCourse> eduList = courseService.getIndex();

        //查询前4条名师
        List<EduTeacher> teacherList = teacherService.getIndex();

        return Result.ok().data("courseList", eduList).data("teacherList", teacherList);
    }
}
