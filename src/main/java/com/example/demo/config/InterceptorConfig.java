package com.example.demo.config;

import com.example.demo.security.interceptor.AccessTokenInterceptor;
import com.example.demo.security.interceptor.LogInterceptor;
import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(@Nonnull InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
        registry.addInterceptor(new AccessTokenInterceptor()).excludePathPatterns("/auth", "/auth/*");
    }
}
