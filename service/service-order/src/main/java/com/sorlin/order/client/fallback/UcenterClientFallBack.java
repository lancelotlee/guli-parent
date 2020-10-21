package com.sorlin.order.client.fallback;

import com.sorlin.order.client.UcenterClient;
import com.sorlin.vo.UcenterMemberWebInfo;

/**
 * @program: guli-parent
 * @description: Ucenter远程调用异常处理类
 * @author: sorlin
 * @create: 2020-08-27 14:22
 */
public class UcenterClientFallBack implements UcenterClient {

    @Override
    public UcenterMemberWebInfo getUserInfo(String id) {
        return null;
    }
}
