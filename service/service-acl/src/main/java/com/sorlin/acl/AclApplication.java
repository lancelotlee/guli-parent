package com.sorlin.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 李松岭
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.sorlin"})
@MapperScan("com.sorlin.acl.mapper")
public class AclApplication {

    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class, args);
    }

}
