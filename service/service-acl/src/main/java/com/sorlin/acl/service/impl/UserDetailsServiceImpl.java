package com.sorlin.acl.service.impl;


import com.sorlin.acl.entity.User;
import com.sorlin.acl.service.PermissionService;
import com.sorlin.acl.service.UserService;
import com.sorlin.serurity.entity.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    private final PermissionService permissionService;

    public UserDetailsServiceImpl(UserService userService, PermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        User user = userService.selectByUsername(username);

        // 判断用户是否存在
        if (null == user){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        com.sorlin.serurity.entity.User curUser = new com.sorlin.serurity.entity.User();
        BeanUtils.copyProperties(user,curUser);

        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}
