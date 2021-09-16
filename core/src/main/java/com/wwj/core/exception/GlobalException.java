package com.wwj.core.exception;

import com.wwj.core.api.ApiCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liaoli
 * @date 2021/4/23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = -2470461654663264392L;

    private Integer errorCode;
    private String message;

    public GlobalException() {
        super();
    }

    public GlobalException(String message) {
        super(message);
        this.message = message;
    }

    public GlobalException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public GlobalException(ApiCode apiCode) {
        super(apiCode.getMessage());
        this.errorCode = apiCode.getCode();
        this.message = apiCode.getMessage();
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

}
