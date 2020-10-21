package com.sorlin.ucenter.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorlin.servicebase.exceptionhandler.GuliException;
import com.sorlin.ucenter.entity.Member;
import com.sorlin.ucenter.entity.vo.LoginInfoVo;
import com.sorlin.ucenter.entity.vo.LoginVo;
import com.sorlin.ucenter.entity.vo.RegisterVo;
import com.sorlin.ucenter.mapper.MemberMapper;
import com.sorlin.ucenter.service.MemberService;
import com.sorlin.util.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-19
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private final RedisTemplate<String,String> redisTemplate;

    public MemberServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //校验参数
        if (StrUtil.isEmpty(mobile) ||
                StrUtil.isEmpty(password) ||
                StrUtil.isEmpty(mobile)) {
            throw new GuliException(20001, "error");
        }

        //获取会员
        Member member = baseMapper.selectOne(new QueryWrapper<Member>().eq("mobile", mobile));
        if (null == member) {
            throw new GuliException(20001, "error");
        }

        //校验密码
        if (!SecureUtil.md5(password).equals(member.getPassword())) {
            throw new GuliException(20001, "error");
        }

        //校验是否被禁用
        if (member.getIsDisabled()) {
            throw new GuliException(20001, "error");
        }

        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //校验参数
        if(StrUtil.isEmpty(mobile) ||
                StrUtil.isEmpty(mobile) ||
                StrUtil.isEmpty(password) ||
                StrUtil.isEmpty(code)) {
            throw new GuliException(20001,"error");
        }

        //校验校验验证码
        //从redis获取发送的验证码
        String mobileCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(mobileCode)) {
            throw new GuliException(20001,"error");
        }

        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<Member>().eq("mobile", mobile));
        if(count > 0) {
            throw new GuliException(20001,"error");
        }

        //添加注册信息到数据库
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(SecureUtil.md5(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }

    @Override
    public LoginInfoVo getLoginInfo(String memberId) {
        Member member = baseMapper.selectById(memberId);
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        BeanUtils.copyProperties(member, loginInfoVo);
        return loginInfoVo;
    }

    @Override
    public Member getByOpenid(String openid) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);

        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer countRegisterByDay(String day) {
        Integer count = baseMapper.countRegisterByDay(day);
        return count;
    }
}
