package com.sorlin.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: guli-parent
 * @description: OSS业务层
 * @author: sorlin
 * @create: 2020-07-31 11:45
 */
public interface OssService {
    /**
     * @Author sorlin
     * @Description //上传头像
     * @Date 12:06 2020/7/31
     * @Param [file]
     * @return java.lang.String
     **/
    String uploadFileAvatar(MultipartFile file);
}
