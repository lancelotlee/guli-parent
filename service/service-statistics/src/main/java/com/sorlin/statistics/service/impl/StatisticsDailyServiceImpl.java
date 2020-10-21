package com.sorlin.statistics.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorlin.Result;
import com.sorlin.statistics.client.UcenterClient;
import com.sorlin.statistics.entity.StatisticsDaily;
import com.sorlin.statistics.mapper.StatisticsDailyMapper;
import com.sorlin.statistics.service.StatisticsDailyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-31
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    private final UcenterClient ucenterClient;

    public StatisticsDailyServiceImpl(UcenterClient ucenterClient) {
        this.ucenterClient = ucenterClient;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void createStatisticsByDay(String day) {

        Result result = ucenterClient.registerCount(day);
        if (!result.getSuccess()) {
            return;
        }
        //删除已存在的统计对象
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.eq("date_calculated", day);
        baseMapper.delete(dayQueryWrapper);


        //获取统计信息
        Integer registerNum = (Integer) result.getData().get("countRegister");
        //TODO
        Integer loginNum = RandomUtil.randomInt(100, 200);
        //TODO
        Integer videoViewNum = RandomUtil.randomInt(100, 200);
        //TODO
        Integer courseNum = RandomUtil.randomInt(100, 200);

        //创建统计对象
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);
    }

    @Override
    public Map<String, Object> getChartData(String begin, String end, String type) {

        QueryWrapper<StatisticsDaily> statisticsDailyQueryWrapper = new QueryWrapper<>();
        statisticsDailyQueryWrapper.between("date_calculated",begin,end);
        statisticsDailyQueryWrapper.select("date_calculated",type);
        statisticsDailyQueryWrapper.orderByAsc("gmt_create");

        List<StatisticsDaily> dayList = baseMapper.selectList(statisticsDailyQueryWrapper);


        Map<String, Object> map = new HashMap<>(4);
        List<Integer> dataList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        map.put("dataList", dataList);
        map.put("dateList", dateList);

        for (StatisticsDaily daily : dayList) {
            dateList.add(daily.getDateCalculated());
            switch (type) {
                case "register_num":
                    dataList.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    dataList.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    dataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    dataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        return map;
    }
}
