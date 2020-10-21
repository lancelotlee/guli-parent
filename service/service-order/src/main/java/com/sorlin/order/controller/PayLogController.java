package com.sorlin.order.controller;


import com.sorlin.Result;
import com.sorlin.order.service.PayLogService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author sorlin
 * @since 2020-08-27
 */
@RestController
@RequestMapping("/order/paylog")
public class PayLogController {

    private final PayLogService payLogService;

    private static final String SUCCESS = "SUCCESS";

    public PayLogController(PayLogService payLogService) {
        this.payLogService = payLogService;
    }

    @GetMapping("/createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo) {
        Map<String, Object> map = payLogService.createNative(orderNo);
        return Result.ok().data(map);
    }

    @GetMapping("/queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        //出错
        if (map == null) {
            return Result.error().message("支付出错");
        }
        //如果成功
        if (SUCCESS.equals(map.get("trade_state"))) {
            //更改订单状态
            payLogService.updateOrderStatus(map);
            return Result.ok().message("支付成功");
        }

        return Result.ok().code(25000).message("支付中");
    }

}

