package com.wwj.common.aspect;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.wwj.authority.util.JwtTokenUtil;
import com.wwj.authority.util.UserInfoUtils;
import com.wwj.common.module.sys.VO.LoginUserInfoVO;
import com.wwj.common.module.sys.entity.SysAdminLoginLog;
import com.wwj.common.module.sys.entity.SysAdminOperationLog;
import com.wwj.common.module.sys.mapper.SysAdminLoginLogMapper;
import com.wwj.common.module.sys.mapper.SysAdminOperationLogMapper;
import com.wwj.core.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Classname OperationLogAspect
 * @Description TODO
 * @Date 2021/7/7 14:04
 * @author by ztsong
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private SysAdminOperationLogMapper oprationLogMapper;

    @Autowired
    private SysAdminLoginLogMapper loginLogMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 设置操作日志切入点   在注解的位置切入代码
     */
    @Pointcut("@annotation(OprationLog)")
    public void operLogPoinCut() {
    }

    /**
     * 设置操作日志切入点   在注解的位置切入代码
     */
    @Pointcut("@annotation(LoginLog)")
    public void loginLogPoinCut() {
    }

    @AfterReturning(value = "loginLogPoinCut()", returning = "result")
    public void saveLoginLog(JoinPoint joinPoint, Object result){
        ApiResult apiResult = (ApiResult) result;
        HttpServletRequest request = getRequest();
        SysAdminLoginLog loginLog = new SysAdminLoginLog();
        // 从返回的token中获取adminId
        LoginUserInfoVO infoVO = (LoginUserInfoVO) apiResult.getData();
        if (Objects.nonNull(infoVO)) {
            String token = infoVO.getToken();
            Integer adminId = jwtTokenUtil.getUserIdFromToken(token);
            loginLog.setAdminId(adminId);
            loginLog.setIp(request.getRemoteAddr());
            UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));
            loginLog.setBrowser(userAgent.getBrowser().getName());
            loginLog.setOs(userAgent.getOs().getName());
            loginLogMapper.insert(loginLog);
        }
    }

    @AfterReturning(value = "operLogPoinCut()", returning = "result")
    public void saveOperLog(JoinPoint joinPoint, Object result) {
        try {
            SysAdminOperationLog oprationLog = getOprationLog(joinPoint);
            // 返回值信息
            oprationLog.setResult("操作成功");
            oprationLogMapper.insert(oprationLog);
        } catch (Exception e) {
            log.error("操作日志错误:", e.getMessage());
        }
    }

    @AfterThrowing(value = "operLogPoinCut()", throwing = "result")
    public void saveErrorOperLog(JoinPoint joinPoint, Throwable result){
        try {
            SysAdminOperationLog oprationLog = getOprationLog(joinPoint);
            // 返回值信息
            oprationLog.setResult("操作失败:"+result.getMessage());
            oprationLogMapper.insert(oprationLog);
        } catch (Exception e) {
            log.error("操作日志错误:", e.getMessage());
        }
    }

    private SysAdminOperationLog getOprationLog(JoinPoint joinPoint) {
        HttpServletRequest request = getRequest();
        SysAdminOperationLog oprationLog = new SysAdminOperationLog();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作
            OprationLog annotation = method.getAnnotation(OprationLog.class);
            if (annotation != null) {
                oprationLog.setModel(annotation.operModul());
                oprationLog.setContent(annotation.operDesc());
            }
            // 操作用户
            oprationLog.setAdminId((Integer) UserInfoUtils.get("userId"));
            // 操作ip
            oprationLog.setIp(request.getRemoteAddr());
        } catch (Exception e) {
            log.error("操作日志错误:", e.getMessage());
        }
        return oprationLog;
    }

    private HttpServletRequest getRequest() {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        return request;
    }

}
