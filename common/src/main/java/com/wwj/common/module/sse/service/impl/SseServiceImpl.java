package com.wwj.common.module.sse.service.impl;

import com.wwj.common.module.sse.exceptiom.SseException;
import com.wwj.common.module.sse.service.SseService;
import com.wwj.common.module.sse.util.HeartBeatTask;
import com.wwj.common.module.sse.util.SseSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @program: frame
 * @className: SseServiceImpl
 * @description: see服务实现
 * @author: 122439195@qq.com
 * @date: 2022-04-26 11:30
 **/
@Service
public class SseServiceImpl implements SseService {

    private static final Logger logger = LoggerFactory.getLogger(SseServiceImpl.class);

    /**
     * 发送心跳线程池
     */
    private static final ScheduledExecutorService heartbeatExecutors = Executors.newScheduledThreadPool(8);

    /**
     * 新建连接
     *
     * @param clientId 客户端ID
     * @return SseEmitter
     */
    @Override
    public SseEmitter start(String clientId) {
        // 设置为0L为永不超时
        // 次数设置30秒超时,方便测试 timeout 事件
        SseEmitter emitter = new SseEmitter(30_000L);
        logger.info("MSG: SseConnect | EmitterHash: {} | ID: {} | Date: {}", emitter.hashCode(), clientId, new Date());
        SseSession.add(clientId, emitter);
        final ScheduledFuture<?> future = heartbeatExecutors.scheduleAtFixedRate(new HeartBeatTask(clientId), 0, 10, TimeUnit.SECONDS);
        emitter.onCompletion(() -> {
            logger.info("MSG: SseConnectCompletion | EmitterHash: {} |ID: {} | Date: {}", emitter.hashCode(), clientId, new Date());
            SseSession.onCompletion(clientId, future);
        });
        emitter.onTimeout(() -> {
            logger.error("MSG: SseConnectTimeout | EmitterHash: {} |ID: {} | Date: {}", emitter.hashCode(), clientId, new Date());
            SseSession.onError(clientId, new SseException("TimeOut(clientId: " + clientId + ")"));
        });
        emitter.onError(t -> {
            logger.error("MSG: SseConnectError | EmitterHash: {} |ID: {} | Date: {}", emitter.hashCode(), clientId, new Date());
            SseSession.onError(clientId, new SseException("Error(clientId: " + clientId + ")"));
        });
        return emitter;
    }

    /**
     * 发送数据
     *
     * @param clientId 客户端ID
     * @return String
     */
    @Override
    public String send(String clientId) {
        if (SseSession.send(clientId, System.currentTimeMillis())) {
            return "Succeed!";
        }
        return "error";
    }

    /**
     * 关闭连接
     *
     * @param clientId 客户端ID
     * @return String
     */
    @Override
    public String close(String clientId) {
        logger.info("MSG: SseConnectClose | ID: {} | Date: {}", clientId, new Date());
        if (SseSession.del(clientId)) return "Succeed!";
        return "Error!";
    }
}
