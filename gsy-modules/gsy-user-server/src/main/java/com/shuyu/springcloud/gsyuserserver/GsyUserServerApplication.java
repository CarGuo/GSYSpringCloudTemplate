package com.shuyu.springcloud.gsyuserserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.shuyu.springcloud.db"})
public class GsyUserServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsyUserServerApplication.class, args);
	}

}

