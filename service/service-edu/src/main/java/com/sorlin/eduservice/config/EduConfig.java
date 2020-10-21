package com.sorlin.eduservice.config;/**
 * @author 李松岭
 * @date 2020-07-23 16:41
 **/

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@program: guli-parent
 *@description: 配置类
 *@author: sorlin
 *@create: 2020-07-23 16:41
 */
@Configuration
@MapperScan("com.sorlin.eduservice.mapper")
public class EduConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
