package com.shuyu.springcloud.db.user.service;

import com.shuyu.springcloud.db.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author shuyu
 * @since 2019-01-25
 */
public interface IUserService extends IService<User> {

    /**
     * 通过手机号查询用户信息
     */
    User selectUserByMobile(String mobile);

    /**
     * 通过name查询用户信息
     */
    User selectUserByUsername(String username);
}
