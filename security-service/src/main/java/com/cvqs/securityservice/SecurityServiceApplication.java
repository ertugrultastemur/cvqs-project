package com.cvqs.securityservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}


}
