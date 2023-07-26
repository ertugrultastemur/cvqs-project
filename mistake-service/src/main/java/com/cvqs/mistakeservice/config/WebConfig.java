package com.cvqs.mistakeservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for Web-related settings.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS (Cross-Origin Resource Sharing) mappings.
     *
     * @param registry the CorsRegistry to be configured
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000/")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
