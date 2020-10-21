package com.sorlin.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sorlin.Result;
import com.sorlin.cms.entity.CrmBanner;
import com.sorlin.cms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author sorlin
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/educms/banneradmin")
public class CrmBannerAdminController {

    private final CrmBannerService bannerService;

    public CrmBannerAdminController(CrmBannerService bannerService) {
        this.bannerService = bannerService;
    }

    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {

        Page<CrmBanner> pageParam = new Page<>(page, limit);
        bannerService.page(pageParam, null);
        return Result.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return Result.ok().data("item", banner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public Result save(@RequestBody CrmBanner banner) {
        bannerService.save(banner);
        return Result.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public Result updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return Result.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        bannerService.removeById(id);
        return Result.ok();
    }

}

