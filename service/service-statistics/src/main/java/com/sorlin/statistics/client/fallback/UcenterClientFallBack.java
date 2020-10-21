package com.sorlin.statistics.client.fallback;

import com.sorlin.Result;
import com.sorlin.statistics.client.UcenterClient;

/**
 * @program: guli-parent
 * @description: 远程调用Ucenter异常处理类
 * @author: sorlin
 * @create: 2020-08-31 11:16
 */
public class UcenterClientFallBack implements UcenterClient {

    @Override
    public Result registerCount(String day) {
        return Result.error();
    }
}
