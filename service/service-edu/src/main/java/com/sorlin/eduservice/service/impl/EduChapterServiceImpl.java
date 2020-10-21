package com.sorlin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorlin.eduservice.entity.EduChapter;
import com.sorlin.eduservice.entity.EduVideo;
import com.sorlin.eduservice.entity.vo.ChapterVo;
import com.sorlin.eduservice.entity.vo.VideoVo;
import com.sorlin.eduservice.mapper.EduChapterMapper;
import com.sorlin.eduservice.service.EduChapterService;
import com.sorlin.eduservice.service.EduVideoService;
import com.sorlin.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-03
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterAndVideoByCourseId(String courseId) {
        //最终要的到的数据列表
        ArrayList<ChapterVo> chapterVoArrayList = new ArrayList<>();

        //获取章节信息
        QueryWrapper<EduChapter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id", courseId);
        queryWrapper1.orderByAsc("sort", "id");
        List<EduChapter> chapters = baseMapper.selectList(queryWrapper1);

        //获取课时信息
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduVideo> videos = eduVideoService.list(queryWrapper2);

        //填充章节vo数据
        for (EduChapter chapter : chapters) {

            //创建章节vo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoArrayList.add(chapterVo);

            //填充课时vo数据
            ArrayList<VideoVo> videoVoArrayList = new ArrayList<>();
            for (EduVideo video : videos) {
                if (chapter.getId().equals(video.getChapterId())) {
                    //创建课时vo对象
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoArrayList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoArrayList);
        }
        return chapterVoArrayList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //根据id查询是否存在视频，如果有则提示用户尚有子节点
        if (eduVideoService.getCountByChapterId(chapterId)) {
            throw new GuliException(20001, "该分章节下存在视频课程，请先删除视频课程");
        }

        int result = baseMapper.deleteById(chapterId);
        return result > 0;
    }

    @Override
    public boolean removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        int count = baseMapper.delete(queryWrapper);
        return count > 0;
    }
}
