package com.sorlin.eduservice.client.fallback;

import com.sorlin.eduservice.client.OrderClient;

/**
 * @program: guli-parent
 * @description: OrderClient远程调用异常处理类
 * @author: sorlin
 * @create: 2020-08-30 15:43
 */
public class OrderClientFallBack implements OrderClient {
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        return false;
    }
}
