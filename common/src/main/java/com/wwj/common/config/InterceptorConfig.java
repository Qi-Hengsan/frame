package com.wwj.common.config;

import com.wwj.common.interceptor.RemoveThreadLocalInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author by ztsong
 * @Classname InterceptorConfig
 * @Description TODO
 * @Date 2021/8/20 13:36
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RemoveThreadLocalInterceptor()).addPathPatterns("/**");
    }
}
