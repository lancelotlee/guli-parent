package com.sorlin.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli-parent
 * @description: 支付模块启动类
 * @author: sorlin
 * @create: 2020-08-27 09:39
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.sorlin"})
@MapperScan("com.sorlin.order.mapper")
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class,args);
    }
}
