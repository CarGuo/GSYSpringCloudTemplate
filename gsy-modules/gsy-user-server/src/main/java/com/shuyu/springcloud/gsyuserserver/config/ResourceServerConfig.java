package com.shuyu.springcloud.gsyuserserver.config;

import com.shuyu.springcloud.entity.global.SecurityConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/***
 * 配置用户服务上的资源管理
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .requestMatchers().antMatchers("/user/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/user/**").authenticated()
//                .and()
//                .httpBasic();

        // @formatter:off
        http
                // Since we want the protected resources to be accessible in the UI as well we need
                // session creation to be allowed (it's disabled by default in 2.0.6)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                //其他接口全部都要认证
                .requestMatchers().anyRequest()
                .and()
                //可以匿名
                .anonymous()
                .and()
                //独立接口处理
                .authorizeRequests()
                //手机请求
                .antMatchers(SecurityConstants.MOBILE_TOKEN_URL).permitAll()
                //.antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
                .antMatchers("/order/**").authenticated();//配置order访问控制，必须认证过后才可以访问
        // @formatter:on

    }
}
