package com.sorlin.eduservice.controller;


import cn.hutool.core.util.StrUtil;
import com.sorlin.Result;
import com.sorlin.eduservice.client.VodClient;
import com.sorlin.eduservice.entity.EduVideo;
import com.sorlin.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author sorlin
 * @since 2020-08-03
 */
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {
    private final EduVideoService eduVideoService;
    private final VodClient vodClient;

    public EduVideoController(EduVideoService eduVideoService, VodClient vodClient) {
        this.eduVideoService = eduVideoService;
        this.vodClient = vodClient;
    }

    @ApiOperation(value = "新增课时")
    @PostMapping("addVideo")
    public Result addVideo(
            @ApiParam(name = "videoForm", value = "课时对象", required = true)
            @RequestBody EduVideo eduVideo){

        eduVideoService.save(eduVideo);
        return Result.ok();
    }

    @ApiOperation(value = "删除课时")
    @DeleteMapping("{id}")
    public Result deleteVideo(
            @ApiParam(name = "videoForm", value = "课时对象", required = true)
            @PathVariable String id){

        EduVideo eduVideo = eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(StrUtil.isNotBlank(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }
        eduVideoService.removeById(id);
        return Result.ok();
    }


}

