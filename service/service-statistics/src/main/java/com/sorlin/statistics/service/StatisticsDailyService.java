package com.sorlin.statistics.service;

import com.sorlin.statistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-31
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStatisticsByDay(String day);

    Map<String, Object> getChartData(String begin, String end, String type);
}
