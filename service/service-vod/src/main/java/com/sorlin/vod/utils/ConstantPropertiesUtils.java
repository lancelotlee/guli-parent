package com.sorlin.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: guli-parent
 * @description: 读取配置文件的工具类
 * @author: sorlin
 * @create: 2020-07-31 11:29
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;
    @Value("${aliyun.vod.file.keyid}")
    private String keyid;


    public static String ACCESS_KEY_SECRET;
    public static String ACCESS_KEY_ID;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_SECRET = keySecret;
        ACCESS_KEY_ID = keyid;
    }
}
