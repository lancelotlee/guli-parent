package com.sorlin.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sorlin.cms.entity.CrmBanner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-17
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getAllBanner();
}
