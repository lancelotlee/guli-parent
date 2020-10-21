package com.sorlin.order.client;

import com.sorlin.order.client.fallback.EduClientFallBack;
import com.sorlin.vo.CourseWebInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: guli-parent
 * @description: edu模块远程调用
 * @author: sorlin
 * @create: 2020-08-27 14:14
 */
@Component
@FeignClient(value = "service-edu",fallback = EduClientFallBack.class)
public interface EduClient {
    @GetMapping("/eduservice/edu-course/getCourseWebInfo/{courseId}")
    CourseWebInfo getCourseWebInfo(@PathVariable("courseId") String courseId);
}
