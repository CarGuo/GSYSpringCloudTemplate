package com.shuyu.springcloud.db.user.service.impl;

import com.shuyu.springcloud.db.user.entity.User;
import com.shuyu.springcloud.db.user.mapper.UserMapper;
import com.shuyu.springcloud.db.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author shuyu
 * @since 2019-01-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User selectUserByMobile(String mobile) {
        return baseMapper.selectUserByMobile(mobile);
    }

    @Override
    public User selectUserByUsername(String username) {
        return baseMapper.selectUserByUsername(username);
    }
}
