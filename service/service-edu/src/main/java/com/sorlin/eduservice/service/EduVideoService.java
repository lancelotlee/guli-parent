package com.sorlin.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sorlin.eduservice.entity.EduVideo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-03
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean getCountByChapterId(String chapterId);

    boolean removeVideoByCourseId(String courseId);
}
