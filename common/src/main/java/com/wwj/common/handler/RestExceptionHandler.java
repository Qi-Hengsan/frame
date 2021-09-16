package com.wwj.common.handler;

import com.wwj.core.api.ApiCode;
import com.wwj.core.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * @Classname RestExceptionHandler
 * @Description 服务层全局响应异常
 * @Date 2021/8/17 10:46
 * @author by ztsong
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * 默认全局异常处理。
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<String> exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return ApiResult.fail(ApiCode.FAIL.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResult<String>> handleValidatedException(Exception e) {
        ApiResult<String> resp = null;

        if (e instanceof MethodArgumentNotValidException) {
            // BeanValidation exception
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            resp = ApiResult.fail(HttpStatus.BAD_REQUEST.value(),
                    ex.getBindingResult().getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining("; "))
            );
        } else if (e instanceof ConstraintViolationException) {
            // BeanValidation GET simple param
            ConstraintViolationException ex = (ConstraintViolationException) e;
            resp = ApiResult.fail(HttpStatus.BAD_REQUEST.value(),
                    ex.getConstraintViolations().stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining("; "))
            );
        } else if (e instanceof BindException) {
            // BeanValidation GET object param
            BindException ex = (BindException) e;
            resp = ApiResult.fail(HttpStatus.BAD_REQUEST.value(),
                    ex.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining("; "))
            );
        }

        log.error("参数校验异常:{}",resp.getMessage());
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public ApiResult<String> handDuplicateKeyException(Exception e) {
        log.error("数据重复：ex={}", e.getMessage());
        return ApiResult.fail(ApiCode.FAIL.getCode(),"数据重复！");
    }

}
