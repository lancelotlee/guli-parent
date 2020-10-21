package com.sorlin.ucenter.controller;


import com.sorlin.Result;
import com.sorlin.servicebase.exceptionhandler.GuliException;
import com.sorlin.ucenter.entity.Member;
import com.sorlin.ucenter.entity.vo.LoginInfoVo;
import com.sorlin.ucenter.entity.vo.LoginVo;
import com.sorlin.ucenter.entity.vo.RegisterVo;
import com.sorlin.ucenter.service.MemberService;
import com.sorlin.util.JwtUtils;
import com.sorlin.vo.UcenterMemberWebInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author sorlin
 * @since 2020-08-19
 */
@RestController
@RequestMapping("/ucenter/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return Result.ok().data("token", token);
    }
    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public Result register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.ok();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public Result getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            LoginInfoVo loginInfoVo = memberService.getLoginInfo(memberId);
            return Result.ok().data("item", loginInfoVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"error");
        }
    }
    //根据token字符串获取用户信息
    @GetMapping("getUserInfo/{id}")
    public UcenterMemberWebInfo getUserInfo(@PathVariable String id){
        Member member = memberService.getById(id);
        UcenterMemberWebInfo ucenterMemberWebInfo = new UcenterMemberWebInfo();
        BeanUtils.copyProperties(member,ucenterMemberWebInfo);
        return ucenterMemberWebInfo;
    }

    @GetMapping(value = "countregister/{day}")
    public Result registerCount(
            @PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return Result.ok().data("countRegister", count);
    }



}

