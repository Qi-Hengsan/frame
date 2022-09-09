package com.wwj.common.module.sse.util;

import com.wwj.common.module.sse.exceptiom.SseException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @program: frame
 * @className: SseEmitterServer
 * @description: 工具类
 * @author: 122439195@qq.com
 * @date: 2022-04-26 11:04
 **/
@Slf4j
public class SseSession {

    private static final Logger logger = LoggerFactory.getLogger(SseSession.class);

    /**
     * Session维护Map
     */
    private static Map<String, org.springframework.web.servlet.mvc.method.annotation.SseEmitter> SESSION = new ConcurrentHashMap<>();

    /**
     * 判断Session是否存在
     *
     * @param id 客户端ID
     * @return 是否存在
     */
    public static boolean exist(String id) {
        return SESSION.get(id) == null;
    }

    /**
     * 增加Session
     *
     * @param id      客户端ID
     * @param emitter SseEmitter
     */
    public static void add(String id, org.springframework.web.servlet.mvc.method.annotation.SseEmitter emitter) {
        final org.springframework.web.servlet.mvc.method.annotation.SseEmitter oldEmitter = SESSION.get(id);
        if (oldEmitter != null) {
            oldEmitter.completeWithError(new SseException("RepeatConnect(Id:" + id + ")"));
        }
        SESSION.put(id, emitter);
    }


    /**
     * 删除Session
     *
     * @param id 客户端ID
     * @return 是否删除成功
     */
    public static boolean del(String id) {
        final org.springframework.web.servlet.mvc.method.annotation.SseEmitter emitter = SESSION.remove(id);
        if (emitter != null) {
            emitter.complete();
            return true;
        }
        return false;
    }

    /**
     * 发送消息
     *
     * @param id  客户端ID
     * @param msg 发送的消息
     * @return 是否发送成功
     */
    public static boolean send(String id, Object msg) {
        final org.springframework.web.servlet.mvc.method.annotation.SseEmitter emitter = SESSION.get(id);
        if (emitter != null) {
            try {
                emitter.send(msg);
                return true;
            } catch (IOException e) {
                logger.error("MSG: SendMessageError-IOException | ID: " + id + " | Date: " + new Date() + " |", e);
                return false;
            }
        }
        return false;
    }

    /**
     * SseEmitter onCompletion 后执行的逻辑
     *
     * @param id     客户端ID
     * @param future 定时任务
     */
    public static void onCompletion(String id, ScheduledFuture<?> future) {
        SESSION.remove(id);
        if (future != null) {
            // SseEmitter断开后需要中断心跳发送
            future.cancel(true);
        }
    }

    /**
     * SseEmitter onTimeout 或 onError 后执行的逻辑
     *
     * @param id 客户端ID
     * @param e  异常
     */
    public static void onError(String id, SseException e) {
        final org.springframework.web.servlet.mvc.method.annotation.SseEmitter emitter = SESSION.get(id);
        if (emitter != null) {
            emitter.completeWithError(e);
        }
    }

}
