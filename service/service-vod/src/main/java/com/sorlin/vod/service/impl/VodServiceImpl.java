package com.sorlin.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.sorlin.servicebase.exceptionhandler.GuliException;
import com.sorlin.vod.service.VodService;
import com.sorlin.vod.utils.ConstantPropertiesUtils;
import com.sorlin.vod.utils.InitVodClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: guli-parent
 * @description: 业务层实现类
 * @author: sorlin
 * @create: 2020-08-09 11:15
 */
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideoOnAly(MultipartFile file) {

        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;

        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;

        String filename = file.getOriginalFilename();
        String title = filename != null ? filename.substring(0, filename.lastIndexOf(".")) : null;
        InputStream inputStream = null;

        String videoId = null;
        try {
            inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, filename, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            //请求视频点播服务的请求ID
            System.out.print("RequestId=" + response.getRequestId() + "\n");
            if (response.isSuccess()) {
                videoId = response.getVideoId();
                System.out.print("VideoId=" + response.getVideoId() + "\n");
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoId;
    }

    @Override
    public void removeVideoById(String id) {
        try {
            DefaultAcsClient acsClient = InitVodClient.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest videoRequest = new DeleteVideoRequest();
            videoRequest.setVideoIds(id);
            acsClient.getAcsResponse(videoRequest);
        } catch (ClientException e) {
            throw new GuliException(20001, "视频删除失败");
        }

    }

    @Override
    public void removeVideoList(List<String> videoIdList) {
        try {
            DefaultAcsClient acsClient = InitVodClient.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest videoRequest = new DeleteVideoRequest();
            String collect = videoIdList.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
            videoRequest.setVideoIds(collect);
            acsClient.getAcsResponse(videoRequest);
        } catch (ClientException e) {
            throw new GuliException(20001, "视频删除失败");
        }
    }

    @Override
    public String getVideoPlayAuth(String videoId) {
        //获取阿里云存储相关常量
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String playAuth = null;
        //初始化
        DefaultAcsClient client = null;
        try {
            client = InitVodClient.initVodClient(accessKeyId, accessKeySecret);
            //请求
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);

            //响应
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            //得到播放凭证
            playAuth = response.getPlayAuth();
            return playAuth;
        } catch (ClientException e) {
            e.printStackTrace();
            return null;
        }
    }
}
