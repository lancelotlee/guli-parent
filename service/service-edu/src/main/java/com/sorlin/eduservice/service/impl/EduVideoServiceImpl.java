package com.sorlin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorlin.eduservice.client.VodClient;
import com.sorlin.eduservice.entity.EduVideo;
import com.sorlin.eduservice.mapper.EduVideoMapper;
import com.sorlin.eduservice.service.EduVideoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-03
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    private final VodClient vodClient;

    public EduVideoServiceImpl(VodClient vodClient) {
        this.vodClient = vodClient;
    }

    @Override
    public boolean getCountByChapterId(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        Integer count = baseMapper.selectCount(queryWrapper);
        return null != count && count > 0;
    }

    @Override
    public boolean removeVideoByCourseId(String courseId) {

        QueryWrapper<EduVideo> queryVideo = new QueryWrapper<>();
        queryVideo.eq("course_id", courseId);
        queryVideo.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(queryVideo);
        List<String> videoIdList = eduVideos.stream()
                .filter(eduVideo -> !eduVideo.getVideoSourceId().isEmpty())
                .map(EduVideo::getVideoSourceId)
                .collect(Collectors.toList());

        if (videoIdList.size() > 0) {
            vodClient.removeVideoList(videoIdList);
        }
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        int count = baseMapper.delete(queryWrapper);
        return count > 0;
    }
}
