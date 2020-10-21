package com.sorlin.statistics.schedule;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.sorlin.statistics.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: guli-parent
 * @description: 定时任务类
 * @author: sorlin
 * @create: 2020-08-31 15:31
 */
@Component
public class ScheduledTask {

    private final StatisticsDailyService statisticsDailyService;

    public ScheduledTask(StatisticsDailyService statisticsDailyService) {
        this.statisticsDailyService = statisticsDailyService;
    }
    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        //获取上一天的日期
        String day = DateUtil.offsetDay(new Date(), -1).toDateStr();
        statisticsDailyService.createStatisticsByDay(day);
    }
}
