package com.shuyu.springcloud.auth.feign.fallback;

import com.shuyu.springcloud.auth.feign.UserService;
import com.shuyu.springcloud.entity.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * FallBack
 */
@Service
public class UserServiceFallbackImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object findUserByUsername(String username) {
        logger.error("调用{}异常:{}", "findUserByUsername", username);
        return null;
    }

    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     * @return UserVo
     */
    @Override
    public Object findUserByMobile(String mobile) {
        logger.error("调用{}异常:{}", "通过手机号查询用户", mobile);
        return null;
    }
}
