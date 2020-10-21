package com.sorlin.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli-parent
 * @description: 会员服务启动类
 * @author: sorlin
 * @create: 2020-08-19 09:38
 */
@ComponentScan({"com.sorlin"})
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.sorlin.ucenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
