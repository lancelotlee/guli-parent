package com.sorlin.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.sorlin.oss.service.OssService;
import com.sorlin.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @program: guli-parent
 * @description: OSS业务层实现类
 * @author: sorlin
 * @create: 2020-07-31 11:46
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String host = "https://";
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = null;
            inputStream = file.getInputStream();

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String originalFilename = file.getOriginalFilename();
            //加上uuid避免文件重名
            originalFilename = uuid + originalFilename;

            // 根据日期生成文件夹
            String datePath = new DateTime().toString("yyyy/MM/dd");
            originalFilename = datePath + "/" + originalFilename;

            ossClient.putObject(bucketName, originalFilename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            return host + bucketName + "." + endpoint + "/" + originalFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
