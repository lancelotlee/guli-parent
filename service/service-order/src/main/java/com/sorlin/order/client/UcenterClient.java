package com.sorlin.order.client;

import com.sorlin.order.client.fallback.UcenterClientFallBack;
import com.sorlin.vo.UcenterMemberWebInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: guli-parent
 * @description: Ucenter远程调用
 * @author: sorlin
 * @create: 2020-08-27 14:14
 */
@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientFallBack.class)
public interface UcenterClient {
    @GetMapping("/ucenter/member/getUserInfo/{id}")
    UcenterMemberWebInfo getUserInfo(@PathVariable("id") String id);


}
