package com.sorlin.eduservice.client;

import com.sorlin.eduservice.client.fallback.OrderClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 李松岭
 */
@Component
@FeignClient(value = "service-order", fallback = OrderClientFallBack.class)
public interface OrderClient {
    //查询订单信息
    @GetMapping("/order/order/isBuyCourse/{memberId}/{courseId}")
    boolean isBuyCourse(@PathVariable("memberId") String memberid, @PathVariable("courseId") String id);
}
