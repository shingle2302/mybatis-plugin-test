package com.shingle.mybatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shing2302
 */
@Configuration
public class MybatisConfig {
    @Bean
    public CustomInterceptor customInterceptor() {
        return new CustomInterceptor();
    }
}
