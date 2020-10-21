package com.sorlin.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sorlin.ucenter.entity.Member;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author sorlin
 * @since 2020-08-19
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer countRegisterByDay(String day);
}
