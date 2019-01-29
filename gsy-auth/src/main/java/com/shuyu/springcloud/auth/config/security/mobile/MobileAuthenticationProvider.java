
package com.shuyu.springcloud.auth.config.security.mobile;

import com.shuyu.springcloud.auth.config.auth.UserDetailsImpl;
import com.shuyu.springcloud.auth.utils.TestUtils;
import com.shuyu.springcloud.entity.UserVO;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class MobileAuthenticationProvider implements AuthenticationProvider {

    //private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;
        //UserVO userVo = userService.findUserByMobile((String) mobileAuthenticationToken.getPrincipal());
        UserVO userVo = TestUtils.generateUserVo();
        if (userVo == null) {
            throw new UsernameNotFoundException("手机号不存在:" + mobileAuthenticationToken.getPrincipal());
        }

        UserDetailsImpl userDetails = buildUserDeatils(userVo);

        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());
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

  /*  public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/
}
