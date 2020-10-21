package com.sorlin.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sorlin.ucenter.entity.Member;
import com.sorlin.ucenter.entity.vo.LoginInfoVo;
import com.sorlin.ucenter.entity.vo.LoginVo;
import com.sorlin.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-19
 */
public interface MemberService extends IService<Member> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    LoginInfoVo getLoginInfo(String memberId);

    Member getByOpenid(String openid);

    Integer countRegisterByDay(String day);
}
