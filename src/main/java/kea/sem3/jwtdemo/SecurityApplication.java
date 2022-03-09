package kea.sem3.jwtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SecurityApplication {

    // CROSS ORIGIN RESOURCE SHARING
    // https://en.wikipedia.org/wiki/Cross-origin_resource_sharing
    // configuration to allow web server to respond to requests from other origins (browser sends a request to web server with information about page origin (localhost, other server...)
    // per default web server only allows its own location (azure.com etc.)
    // allowedOrigins("*") --> allow all
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
