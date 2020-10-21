package com.sorlin.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: guli-parent
 * @description: 统计分析模块启动类
 * @author: sorlin
 * @create: 2020-08-31 10:58
 */
@EnableScheduling
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.sorlin.statistics.mapper")
@ComponentScan(basePackages = {"com.sorlin"})
public class StatisticsDailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsDailyApplication.class, args);
    }

}
