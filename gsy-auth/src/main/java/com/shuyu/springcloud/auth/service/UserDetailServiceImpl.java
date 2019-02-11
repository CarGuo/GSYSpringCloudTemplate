package com.shuyu.springcloud.auth.service;

import com.shuyu.springcloud.auth.config.auth.UserDetailsImpl;
import com.shuyu.springcloud.auth.feign.UserService;
import com.shuyu.springcloud.auth.utils.OAuthUtils;
import com.shuyu.springcloud.entity.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 获取用户
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> obj = (Map<String, Object>)userService.findUserByUsername(username);
        UserVO userVo = OAuthUtils.getUserVoByMap(obj);
        if (userVo == null) {
            throw new UsernameNotFoundException("用户名不存在或者密码错误");
        }
        return new UserDetailsImpl(userVo);
    }
}
