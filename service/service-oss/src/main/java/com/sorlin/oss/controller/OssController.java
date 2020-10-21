package com.sorlin.oss.controller;

import com.sorlin.Result;
import com.sorlin.oss.service.OssService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: guli-parent
 * @description: OSS控制层
 * @author: sorlin
 * @create: 2020-07-31 11:44
 */
@RestController
@RequestMapping("/eduoss/fileoss")

public class OssController {
    private final OssService ossService;

    public OssController(OssService ossService) {
        this.ossService = ossService;
    }

    @PostMapping("upload")
    public Result uploadOssFile(@ApiParam(name = "file", value = "文件", required = true)
                                @RequestParam("file") MultipartFile file) {
        String uploadUrl = ossService.uploadFileAvatar(file);

        return Result.ok().data("url",uploadUrl);
    }
}
