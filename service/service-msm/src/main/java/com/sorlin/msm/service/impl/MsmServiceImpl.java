package com.sorlin.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sorlin.msm.service.MsmService;
import com.sorlin.msm.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: guli-parent
 * @description: MsmService实现类
 * @author: sorlin
 * @create: 2020-08-18 19:00
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "15196448926");
        request.putQueryParameter("SignName", "我的在线学习网站");
        request.putQueryParameter("TemplateCode", "SMS_199774056");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }
}
