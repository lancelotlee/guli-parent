package com.sorlin.eduservice.client.fallback;

import com.sorlin.Result;
import com.sorlin.eduservice.client.VodClient;

import java.util.List;

/**
 * @program: guli-parent
 * @description: VodClient远程调用异常处理类
 * @author: sorlin
 * @create: 2020-08-14 15:45
 */
public class VodClientFallBack implements VodClient {
    @Override
    public Result removeVideo(String id) {
        return Result.error().message("删除视频失败");
    }

    @Override
    public Result removeVideoList(List<String> videoIdList) {
        return Result.error().message("批量删除视频失败");
    }
}
