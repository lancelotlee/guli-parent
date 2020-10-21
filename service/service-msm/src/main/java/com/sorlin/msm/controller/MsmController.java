package com.sorlin.msm.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.sorlin.Result;
import com.sorlin.msm.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: guli-parent
 * @description: MSM控制层
 * @author: sorlin
 * @create: 2020-08-18 19:00
 */
@Api(tags = {"短信发送"})
@RestController
@RequestMapping("/edumsm/msm")
public class MsmController {

    private final RedisTemplate<String,String> redisTemplate;

    private final MsmService msmService;

    public MsmController(MsmService msmService, RedisTemplate<String, String> redisTemplate) {
        this.msmService = msmService;
        this.redisTemplate = redisTemplate;
    }
    @ApiOperation(value = "电话号码")
    @GetMapping("/send/{phone}")
    public Result sendMsm(@ApiParam(name = "phone", value = "电话号码", required = true) @PathVariable String phone){

        String code = redisTemplate.opsForValue().get(phone);
        if(StrUtil.isNotEmpty(code)){
            return Result.ok();
        }
        code = RandomUtil.randomNumbers(6);
        Map<String, Object> param = new HashMap<>(16);
        param.put("code",code);
        boolean isSend = msmService.send(param,phone);
        if (isSend){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok();
        }else {
            return Result.error().message("短信发送失败");
        }
    }
}
