package com.wwj.common.interceptor;

import com.wwj.authority.util.UserInfoUtils;
import com.wwj.common.utils.ImportErrorInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by ztsong
 * @Classname RemoveThreadLocalInterceptor
 * @Description 给ThreadLocal remove操作避免发生内存泄露
 * @Date 2021/8/20 13:21
 */
@Component
@Slf4j
public class RemoveThreadLocalInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfoUtils.remove();
        ImportErrorInfoUtils.remove();
    }
}
