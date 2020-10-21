package com.sorlin.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorlin.cms.entity.CrmBanner;
import com.sorlin.cms.mapper.CrmBannerMapper;
import com.sorlin.cms.service.CrmBannerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-17
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    @Cacheable(value = "banner",key="'selectIndexList'")
    public List<CrmBanner> getAllBanner() {
        QueryWrapper<CrmBanner> crmBannerQueryWrapper = new QueryWrapper<>();
        crmBannerQueryWrapper.orderByDesc("id");
        crmBannerQueryWrapper.last("limit 2");
        List<CrmBanner> crmBanners = baseMapper.selectList(crmBannerQueryWrapper);
        return crmBanners;
    }
}
