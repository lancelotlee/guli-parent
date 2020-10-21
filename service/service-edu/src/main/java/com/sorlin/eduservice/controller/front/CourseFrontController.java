package com.sorlin.eduservice.controller.front;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sorlin.Result;
import com.sorlin.eduservice.client.OrderClient;
import com.sorlin.eduservice.entity.EduCourse;
import com.sorlin.eduservice.entity.frontvo.CourseQueryVo;
import com.sorlin.eduservice.entity.frontvo.CourseWebVo;
import com.sorlin.eduservice.entity.vo.ChapterVo;
import com.sorlin.eduservice.service.EduChapterService;
import com.sorlin.eduservice.service.EduCourseService;
import com.sorlin.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: guli-parent
 * @description: 课程前台显示
 * @author: sorlin
 * @create: 2020-08-25 14:05
 */
@Api(tags = {"课程前台显示"})
@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {


    private final EduChapterService eduChapterService;
    private final EduCourseService eduCourseService;
    private final OrderClient orderClient;

    public CourseFrontController(EduCourseService eduCourseService, EduChapterService eduChapterService, OrderClient orderClient) {
        this.eduCourseService = eduCourseService;
        this.eduChapterService = eduChapterService;
        this.orderClient = orderClient;
    }

    @ApiOperation(value = "分页课程列表")
    @PostMapping("getCourseFrontList/{page}/{limit}")
    public Result getCourseFrontList(@ApiParam(name = "page", value = "当前页码", required = true)
                                     @PathVariable Long page,
                                     @ApiParam(name = "limit", value = "每页记录数", required = true)
                                     @PathVariable Long limit,
                                     @ApiParam(name = "courseQueryVo", value = "课程查询对象", required = true)
                                     @RequestBody(required = false) CourseQueryVo courseQueryVo) {
        Page<EduCourse> pageParam = new Page<>(page, limit);
        Map<String, Object> map = eduCourseService.getCourseFrontList(pageParam, courseQueryVo);
        return Result.ok().data(map);

    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "getCourseFrontInfo/{courseId}")
    public Result getCourseFrontInfo(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId,
            HttpServletRequest httpServletRequest) {
        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);
        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = eduChapterService.getChapterAndVideoByCourseId(courseId);
        String memberId = JwtUtils.getMemberIdByJwtToken(httpServletRequest);
        boolean buyCourse = false;
        if (StrUtil.isNotEmpty(memberId)) {
            //远程调用，判断课程是否被购买
            buyCourse = orderClient.isBuyCourse(JwtUtils.getMemberIdByJwtToken(httpServletRequest), courseId);
        }
        return Result.ok().data("courseWebVo", courseWebVo).data("chapterVoList", chapterVoList).data("isbuy", buyCourse);
    }


}
