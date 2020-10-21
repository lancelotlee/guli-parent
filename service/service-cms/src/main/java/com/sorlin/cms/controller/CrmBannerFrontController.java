package com.sorlin.cms.controller;


import com.sorlin.Result;
import com.sorlin.cms.entity.CrmBanner;
import com.sorlin.cms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author sorlin
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/educms/bannerfront")
public class CrmBannerFrontController {

    private final CrmBannerService bannerService;

    public CrmBannerFrontController(CrmBannerService bannerService) {
        this.bannerService = bannerService;
    }

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public Result index() {

        List<CrmBanner> list = bannerService.getAllBanner();
        return Result.ok().data("bannerList", list);
    }

}

