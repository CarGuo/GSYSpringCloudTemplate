package com.shuyu.springcloud.auth.config.auth;

import com.shuyu.springcloud.auth.mobile.MobileSecurityConfigurer;
import com.shuyu.springcloud.entity.global.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 * 资源服务器，可配置到各个微服务中
 * ResourceServerConfigurerAdapter 配置会覆盖 WebSecurityConfigurerAdapter
 */
@Configuration
@EnableResourceServer
class GSYResServerConfiguration extends ResourceServerConfigurerAdapter {



    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;

    @Autowired
    public MobileSecurityConfigurer mobileSecurityConfigurer;


    private static final String DEMO_RESOURCE_ID = "order";

    /**
     * 资源安全配置
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
        resources.expressionHandler(expressionHandler);
    }

    /**
     * http安全配置
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                // Since we want the protected resources to be accessible in the UI as well we need
                // session creation to be allowed (it's disabled by default in 2.0.6)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                //其他接口全部都要认证
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                //.requestMatchers().anyRequest()
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

        //配置手机登录
        http.apply(mobileSecurityConfigurer);
    }

    /**
     * 配置解决 spring-security-oauth问题
     * https://github.com/spring-projects/spring-security-oauth/issues/730
     *
     * @param applicationContext ApplicationContext
     * @return OAuth2WebSecurityExpressionHandler
     */
    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

}
