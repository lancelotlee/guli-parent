package com.sorlin.order.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sorlin.Result;
import com.sorlin.order.entity.Order;
import com.sorlin.order.service.OrderService;
import com.sorlin.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author sorlin
 * @since 2020-08-27
 */
@Api(tags = {"订单 前端控制器"})
@RestController
@RequestMapping("/order/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "创建订单")
    @PostMapping("createOrder/{courseId}")
    public Result createOrder(@ApiParam(name = "courseId", value = "课程id", required = true)
                              @PathVariable String courseId,

                              HttpServletRequest request) {
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        if (StrUtil.isEmpty(memberIdByJwtToken)) {
            return Result.error().code(20001).message("请登录");
        }
        String orderNo = orderService.createOrders(courseId, memberIdByJwtToken);
        if (StrUtil.isEmpty(orderNo)) {
            return Result.error().code(20001).message("创建订单异常");
        }
        return Result.ok().data("orderId", orderNo);
    }

    @ApiOperation(value = "获取订单")
    @GetMapping("getOrder/{orderId}")
    public Result getOrder(@ApiParam(name = "orderId", value = "订单id", required = true)
                           @PathVariable String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderId);
        Order order = orderService.getOne(wrapper);
        return Result.ok().data("item", order);
    }

    @GetMapping("isBuyCourse/{memberId}/{courseId}")
    public boolean isBuyCourse(@PathVariable String memberId,
                               @PathVariable String courseId) {
        //订单状态是1表示支付成功
        int count = orderService.count(new QueryWrapper<Order>()
                .eq("member_id", memberId)
                .eq("course_id", courseId)
                .eq("status", 1));
        return count > 0;
    }
}

