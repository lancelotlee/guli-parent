package com.sorlin.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: guli-parent
 * @description: 业务层
 * @author: sorlin
 * @create: 2020-08-09 11:14
 */
public interface VodService {
    String uploadVideoOnAly(MultipartFile file);

    void removeVideoById(String id);

    void removeVideoList(List<String> videoIdList);

    String getVideoPlayAuth(String videoId);
}
