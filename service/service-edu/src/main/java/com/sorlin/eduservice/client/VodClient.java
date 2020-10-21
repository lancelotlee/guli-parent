package com.sorlin.eduservice.client;

import com.sorlin.Result;
import com.sorlin.eduservice.client.fallback.VodClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 李松岭
 */
@FeignClient(value = "service-vod",fallback = VodClientFallBack.class)
@Component
public interface VodClient {
    @DeleteMapping("/eduvod/video/removeVideo/{id}")
    Result removeVideo(@PathVariable("id") String id);

    @DeleteMapping("/eduvod/video/delete-batch")
    Result removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
