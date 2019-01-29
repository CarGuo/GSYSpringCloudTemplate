package com.shuyu.springcloud.auth.service;

import com.shuyu.springcloud.auth.config.auth.UserDetailsImpl;
import com.shuyu.springcloud.auth.utils.TestUtils;
import com.shuyu.springcloud.entity.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    //@Autowired
    //private UserService userService;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        //UserVO userVo = userService.findUserByUsername(username);
        UserVO userVO = TestUtils.generateUserVo();
        if (userVO == null) {
            throw new UsernameNotFoundException("用户名不存在或者密码错误");
        }
        return new UserDetailsImpl(userVO);
    }
}
