package com.sorlin.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli-parent
 * @description: CMS启动类
 * @author: sorlin
 * @create: 2020-08-17 16:10
 */
@SpringBootApplication
@ComponentScan({"com.sorlin"})
@MapperScan("com.sorlin.cms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
