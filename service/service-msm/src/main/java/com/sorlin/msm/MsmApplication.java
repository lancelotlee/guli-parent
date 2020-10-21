package com.sorlin.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli-parent
 * @description: MSM服务启动类
 * @author: sorlin
 * @create: 2020-08-18 18:59
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.sorlin"})
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
