package com.shuyu.springcloud.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 对外提供用户信息。用于资源服务获取用户信息判断
 */
@RestController
@RequestMapping("/user")
public class MemberController {

    @GetMapping("/member")
    public Principal user(Principal member) {
        return member;
    }

}
