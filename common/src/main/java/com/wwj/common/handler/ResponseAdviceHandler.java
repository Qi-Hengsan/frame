package com.wwj.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwj.core.api.ApiResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Classname ResponseAdviceHandler
 * @Description 统一响应处理器
 * @Date 2021/8/17 10:44
 * @author by ztsong
 */
@RestControllerAdvice(basePackages = "com.httech.dmyl")
@Slf4j
public class ResponseAdviceHandler implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof String){
            return objectMapper.writeValueAsString(ApiResult.ok(o));
        }
        if(o instanceof ApiResult){
            return o;
        }
        return ApiResult.ok(o);
    }
}