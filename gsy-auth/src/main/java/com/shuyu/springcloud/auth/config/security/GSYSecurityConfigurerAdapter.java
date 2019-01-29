package com.shuyu.springcloud.auth.config.security;


import com.shuyu.springcloud.auth.config.security.mobile.MobileSecurityConfigurer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration
@EnableWebSecurity
public class GSYSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


    @Autowired
    private MobileSecurityConfigurer mobileSecurityConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.authorizeRequests();
        //filterIgnorePropertiesConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());

//        registry.antMatchers("/product/**").permitAll();
//        registry.anyRequest().authenticated()
//                .and()
//                .csrf().disable();

        http.requestMatchers().anyRequest()
                .and().authorizeRequests().antMatchers("/oauth/*").permitAll();

        http.apply(mobileSecurityConfigurer);
    }

    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }
}
