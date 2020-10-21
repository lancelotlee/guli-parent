package com.sorlin.statistics.controller;


import com.sorlin.Result;
import com.sorlin.statistics.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author sorlin
 * @since 2020-08-31
 */
@Api(tags = {"统计分析"})
@RestController
@RequestMapping("/statistics/sta")
public class StatisticsDailyController {

    private final StatisticsDailyService statisticsDailyService;

    public StatisticsDailyController(StatisticsDailyService statisticsDailyService) {
        this.statisticsDailyService = statisticsDailyService;
    }

    @ApiOperation(value = "按日期统计")
    @PostMapping("{day}")
    public Result createStatisticsByDate(@ApiParam(name = "day", value = "日期", required = true)
                                         @PathVariable String day) {
        statisticsDailyService.createStatisticsByDay(day);
        return Result.ok();
    }
    @ApiOperation(value = "按日期类型查询")
    @GetMapping("showchart/{begin}/{end}/{type}")
    public Result showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = statisticsDailyService.getChartData(begin, end, type);
        return Result.ok().data(map);
    }

}

