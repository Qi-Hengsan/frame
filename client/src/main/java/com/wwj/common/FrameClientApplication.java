package com.wwj.common;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author by ztsong
 * @Classname FrameClientApplication
 * @Description TODO
 * @Date 2021/8/19 11:27
 */
@SpringBootApplication
@MapperScan("com.wwj.common.**.mapper")
public class FrameClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameClientApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
