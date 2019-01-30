package com.shuyu.springcloud.auth.config.security;

import com.shuyu.springcloud.auth.config.security.mobile.MobileSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security 配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法级的权限认证
public class GSYSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    public MobileSecurityConfigurer mobileSecurityConfigurer;

    /**
     * 不配置 formLogin() ，分离
     * antMatchers 的顺序很重要
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 定义哪些URL需要被保护、哪些不需要被保护
                .authorizeRequests()
                //打开授权
                .antMatchers("/oauth/*").permitAll()
                .and()
                .authorizeRequests()
                // 任何请求,登录后可以访问
                .anyRequest()
                .authenticated()
                .and()
                // 关闭csrf防护
                .csrf().disable();

        //配置手机登录
        http.apply(mobileSecurityConfigurer);
    }

    /**
     * Spring Boot 2 开始，要 bean 注入 AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
