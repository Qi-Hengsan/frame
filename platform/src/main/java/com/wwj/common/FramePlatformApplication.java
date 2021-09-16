package com.wwj.common;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author by ztsong
 * @Classname FramePlatformApplication
 * @Description TODO
 * @Date 2021/8/19 11:28
 */
@SpringBootApplication
@MapperScan("com.wwj.common.**.mapper")
public class FramePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(FramePlatformApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
