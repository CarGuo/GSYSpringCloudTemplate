package com.shuyu.springcloud.gsyuserserver.controller;


import com.shuyu.springcloud.db.user.entity.User;
import com.shuyu.springcloud.db.user.service.IUserService;
import com.shuyu.springcloud.entity.utils.ResponseUtil;
import com.shuyu.springcloud.gsyuserserver.utils.ConversionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author shuyu
 * @since 2019-01-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public IUserService userService;

    /**
     * 通过手机号查询用户及其角色信息
     *
     * @param mobile 手机号
     */
    @GetMapping("/findUserByMobile/{mobile}")
    public Object findUserByMobile(@PathVariable String mobile) {
        User user = userService.selectUserByMobile(mobile);
        return ResponseUtil.ok(ConversionUtils.converUserToDto(user));
    }

    /**
     * 通过用户名查询用户及其角色信息
     *
     * @param username 用户名
     */
    @GetMapping("/findUserByUsername/{username}")
    public Object findUserByUsername(@PathVariable String username) {
        User user = userService.selectUserByUsername(username);
        return ResponseUtil.ok(ConversionUtils.converUserToDto(user));
    }


}

