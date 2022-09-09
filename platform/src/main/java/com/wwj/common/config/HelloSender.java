package com.wwj.common.config;

import com.wwj.common.constants.MQField;
import com.wwj.common.module.teacher.entity.Teacher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Hello消息生产者
 **/
@Component
public class HelloSender {

    @Resource
    private AmqpTemplate rabbitTemplate;

    public boolean send(String message) throws Exception {
        boolean isOK = false;

        if (StringUtils.isEmpty(message)) {
            System.out.println("消息为空");
            return isOK;
        }

        rabbitTemplate.convertAndSend(MQField.HELLO_STRING_QUEUE, message);

        isOK = true;

        System.out.println(String.format("HelloSender发送字符串消息结果：%s", isOK));

        return isOK;
    }

    public boolean send(Teacher teacher) throws Exception {

        boolean isOK = false;

        rabbitTemplate.convertAndSend(MQField.HELLO_GOODS_QUEUE, teacher);

        isOK = true;

        System.out.println(String.format("HelloSender发送对象消息结果：%s", isOK));

        return isOK;

    }
}
