package com.wwj.common.module.sse.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @program: frame
 * @className: SseService
 * @description: see服务
 * @author: 122439195@qq.com
 * @date: 2022-04-26 11:29
 **/
public interface SseService {

    /**
     * 新建连接
     *
     * @param clientId 客户端ID
     * @return SseEmitter
     */
    SseEmitter start(String clientId);

    /**
     * 发送数据
     *
     * @param clientId 客户端ID
     * @return String
     */
    String send(String clientId);

    /**
     * 关闭连接
     *
     * @param clientId 客户端ID
     * @return String
     */
    String close(String clientId);
}
