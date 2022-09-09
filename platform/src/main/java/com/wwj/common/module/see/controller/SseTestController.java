package com.wwj.common.module.see.controller;

import com.wwj.common.module.sse.service.SseService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;

/**
 * @program: frame
 * @className: SseTestController
 * @description: see测试
 * @author: 122439195@qq.com
 * @date: 2022-04-26 11:24
 **/
@RestController
@RequestMapping("/sse")
@Api(value = "see测试", tags = "see测试")
public class SseTestController {

    private static final Logger logger = LoggerFactory.getLogger(SseTestController.class);

    @Resource
    private SseService sseService;

    @GetMapping("/start")
    public SseEmitter start(@RequestParam String clientId) {
        return sseService.start(clientId);
    }

    /**
     * 将SseEmitter对象设置成完成
     *
     * @param clientId 客户端id
     * @return SseEmitter
     */
    @GetMapping("/end")
    public String close(String clientId) {
        return sseService.close(clientId);
    }
}
