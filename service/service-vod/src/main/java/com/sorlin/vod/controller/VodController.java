package com.sorlin.vod.controller;

import com.sorlin.Result;
import com.sorlin.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: guli-parent
 * @description: 控制层
 * @author: sorlin
 * @create: 2020-08-09 11:14
 */
@Api(tags = {"视频点播"})
@RestController
@RequestMapping("/eduvod/video")
public class VodController {
    private final VodService vodService;

    public VodController(VodService vodService) {
        this.vodService = vodService;
    }

    @ApiOperation(value = "上传视频")
    @PostMapping("/uploadVideo")
    public Result uploadVideo(@ApiParam(name = "file", value = "文件", required = true) MultipartFile file) {
        String videoId = vodService.uploadVideoOnAly(file);
        return Result.ok().data("videoId",videoId);

    }
    @ApiOperation(value = "删除视频")
    @DeleteMapping("/removeVideo/{id}")
    public Result removeVideo(@ApiParam(name = "file", value = "文件", required = true) @PathVariable String id) {
        vodService.removeVideoById(id);
        return Result.ok();

    }

    @DeleteMapping("delete-batch")
    public Result removeVideoList(@ApiParam(name = "file", value = "文件", required = true) @RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeVideoList(videoIdList);
        return Result.ok();

    }
    @GetMapping("get-play-auth/{videoId}")
    public Result getVideoPlayAuth(@PathVariable("videoId") String videoId) throws Exception {

        String playAuth = vodService.getVideoPlayAuth(videoId);

        //返回结果
        return Result.ok().message("获取凭证成功").data("playAuth", playAuth);
    }

}
