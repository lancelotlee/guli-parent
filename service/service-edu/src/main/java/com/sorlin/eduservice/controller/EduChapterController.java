package com.sorlin.eduservice.controller;


import com.sorlin.Result;
import com.sorlin.eduservice.entity.EduChapter;
import com.sorlin.eduservice.entity.vo.ChapterVo;
import com.sorlin.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = {"课程章节管理"})
@RestController
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {
    private final EduChapterService eduChapterService;

    public EduChapterController(EduChapterService eduChapterService) {
        this.eduChapterService = eduChapterService;
    }

    @ApiOperation(value = "根据ID查询章节和小节")
    @GetMapping("getChapterAndVideo/{courseId}")
    public Result getChapterAndVideo(@ApiParam(name = "courseId", value = "课程ID", required = true)
                                     @PathVariable String courseId) {

        List<ChapterVo> chapterAndVideoByCourseId = eduChapterService.getChapterAndVideoByCourseId(courseId);
        return Result.ok().data("items", chapterAndVideoByCourseId);
    }
    @ApiOperation(value = "添加章节")
    @PostMapping("addChapter")
    public Result addChapter(@ApiParam(name = "eduChapter", value = "章节", required = true)
                             @RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return Result.ok();
    }

    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("getChapterInfo/{chapterId}")
    public Result getChapterInfo(@ApiParam(name = "id", value = "章节ID", required = true)
                                 @PathVariable String chapterId) {

        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return Result.ok().data("chapter", eduChapter);
    }

    @ApiOperation(value = "更新章节")
    @PutMapping("updateChapter")
    public Result updateChapter(@ApiParam(name = "eduChapter", value = "章节", required = true)
                                @RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return Result.ok();
    }
    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("{chapterId}")
    public Result deleteChapter(@ApiParam(name = "eduChapter", value = "章节", required = true)
                                @PathVariable String chapterId) {
        eduChapterService.deleteChapter(chapterId);
        return Result.ok();
    }


}

