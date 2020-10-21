package com.sorlin.statistics.client;

import com.sorlin.Result;
import com.sorlin.statistics.client.fallback.UcenterClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-ucenter", fallback = UcenterClientFallBack.class)
public interface UcenterClient {
    @GetMapping(value = "/ucenter/member/countregister/{day}")
    public Result registerCount(@PathVariable("day") String day);
}

