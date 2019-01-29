package com.shuyu.springcloud.auth.config.security;


import com.shuyu.springcloud.auth.config.security.mobile.MobileSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class GSYSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


    @Autowired
    private MobileSecurityConfigurer mobileSecurityConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().anyRequest()
                .and().authorizeRequests().antMatchers("/oauth/*").permitAll();

        http.apply(mobileSecurityConfigurer);
    }


    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
