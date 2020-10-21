package com.sorlin.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sorlin.order.entity.Order;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-27
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberIdByJwtToken);
}
