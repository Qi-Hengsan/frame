package com.wwj.common.module.sse.exceptiom;

/**
 * @program: frame
 * @className: SseException
 * @description: see异常管理
 * @author: 122439195@qq.com
 * @date: 2022-04-26 11:22
 **/
public class SseException extends RuntimeException {
    
    public SseException() {
    }

    public SseException(String message) {
        super(message);
    }

    public SseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SseException(Throwable cause) {
        super(cause);
    }

    public SseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
