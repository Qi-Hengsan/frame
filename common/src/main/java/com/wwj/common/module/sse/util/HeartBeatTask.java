package com.wwj.common.module.sse.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @program: frame
 * @className: HeartBeatTask
 * @description: 心跳
 * @author: 122439195@qq.com
 * @date: 2022-04-26 11:27
 **/
public class HeartBeatTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(HeartBeatTask.class);

    private final String clientId;

    public HeartBeatTask(String clientId) {
        // 这里可以按照业务传入需要的数据
        this.clientId = clientId;
    }

    @Override
    public void run() {
        logger.info("MSG: SseHeartbeat | ID: {} | Date: {}", clientId, new Date());
        SseSession.send(clientId, "ping");
    }
}
