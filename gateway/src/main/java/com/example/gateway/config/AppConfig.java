/**
 * Configuration class for application-specific configurations.
 */
package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    /**
     * Creates a new instance of RestTemplate.
     *
     * @return the RestTemplate instance
     */
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
