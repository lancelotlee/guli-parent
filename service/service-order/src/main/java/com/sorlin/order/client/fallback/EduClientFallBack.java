package com.sorlin.order.client.fallback;

import com.sorlin.order.client.EduClient;
import com.sorlin.vo.CourseWebInfo;

/**
 * @program: guli-parent
 * @description: Edu远程调用异常处理类
 * @author: sorlin
 * @create: 2020-08-27 14:20
 */
public class EduClientFallBack implements EduClient {

    @Override
    public CourseWebInfo getCourseWebInfo(String courseId) {
        return null;
    }
}
