
package com.shuyu.springcloud.auth.mobile;

import com.shuyu.springcloud.auth.config.auth.UserDetailsImpl;
import com.shuyu.springcloud.auth.utils.TestUtils;
import com.shuyu.springcloud.entity.UserVO;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义手机登录校验
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {

    //private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;

        //TODO 判断code是否为空，是否正确匹配 redis 中的验证码


        // todo userService
        // UserVO userVo = userService.findUserByMobile((String) mobileAuthenticationToken.getPrincipal());

        UserVO userVo = TestUtils.generateUserVo();

        if (userVo == null) {
            throw new UsernameNotFoundException("手机号不存在:" + mobileAuthenticationToken.getPrincipal());
        }

        UserDetailsImpl userDetails = buildUserDeatils(userVo);

        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails, authentication.getCredentials(),  userDetails.getAuthorities());
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    private UserDetailsImpl buildUserDeatils(UserVO userVo) {
        return new UserDetailsImpl(userVo);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /*public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/
}