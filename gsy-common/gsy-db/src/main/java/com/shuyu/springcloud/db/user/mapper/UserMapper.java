package com.shuyu.springcloud.db.user.mapper;

import com.shuyu.springcloud.db.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author shuyu
 * @since 2019-01-25
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过手机号查询用户信息
     */
    User selectUserByMobile(@Param("mobile") String mobile);

    /**
     * 通过name查询用户信息
     */
    User selectUserByUsername(@Param("username") String username);

}
