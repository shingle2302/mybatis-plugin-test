package com.shingle.mybatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shingle2302
 * @email shingle2302@qq.com
 */
@Configuration
public class MybatisConfig {
    @Bean
    public CustomInterceptor customInterceptor() {
        return new CustomInterceptor();
    }
}
