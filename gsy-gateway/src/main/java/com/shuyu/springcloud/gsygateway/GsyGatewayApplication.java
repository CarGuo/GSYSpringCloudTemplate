package com.shuyu.springcloud.gsygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableZuulProxy
@EnableGlobalMethodSecurity(prePostEnabled = true)
//启动单点登录授权
@EnableOAuth2Sso
public class GsyGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsyGatewayApplication.class, args);
	}

}

