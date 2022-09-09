package com.wwj.common.module.rabbitmq.controller;

import com.wwj.common.config.HelloSender;
import com.wwj.core.api.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: frame
 * @className: RabbitController
 * @description:
 * @author: 122439195@qq.com
 * @date: 2022-02-10 15:19
 **/
@RestController
@RequestMapping("/rabbit")
@Api(value = "消息队列", tags = {"消息队列"})
public class RabbitController {

    @Resource
    private HelloSender helloSender;

    @GetMapping(value = "/hello/sendmsg")
    @ApiOperation("简单字符串消息测试")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "message", required = true, value = "字符串消息", dataType = "String")
    })
    public ApiResult<?> sendMsg(String message) throws Exception {

        boolean isOK = helloSender.send(message);

        return ApiResult.ok(isOK);
    }
}
