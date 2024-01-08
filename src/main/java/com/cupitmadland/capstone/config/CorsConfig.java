package com.cupitmadland.capstone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Configuration class to enable Cross-Origin Resource Sharing (CORS) for specific endpoints.
 * Used initially when attempting to use AJAX to move single product data to the shopping cart.
 * However, the strategy was later switched to DTP and HTTP session.
 * Keeping this here to possibly use in future updates.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
