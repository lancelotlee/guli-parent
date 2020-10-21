package com.sorlin.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sorlin.eduservice.entity.EduChapter;
import com.sorlin.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-03
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterAndVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    boolean removeChapterByCourseId(String courseId);
}
