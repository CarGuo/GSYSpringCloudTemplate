package com.shuyu.springcloud.tempate.gsycenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GsyCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsyCenterApplication.class, args);
	}

}

