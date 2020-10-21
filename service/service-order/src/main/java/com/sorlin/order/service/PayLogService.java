package com.sorlin.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sorlin.order.entity.PayLog;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-27
 */
public interface PayLogService extends IService<PayLog> {

    Map<String,Object> createNative(String orderNo);

    void updateOrderStatus(Map<String, String> map);

    Map<String, String> queryPayStatus(String orderNo);
}
