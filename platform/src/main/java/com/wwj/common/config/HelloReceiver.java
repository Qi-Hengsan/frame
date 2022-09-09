package com.wwj.common.config;

import cn.hutool.core.util.SerializeUtil;
import com.wwj.common.constants.MQField;
import com.wwj.common.module.teacher.entity.Teacher;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Hello消息消费者
 **/
@Component
public class HelloReceiver {

    @RabbitListener(queues = MQField.HELLO_STRING_QUEUE)
    @RabbitHandler
    public void process(String message) {

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("HelloReceiver接收到的字符串消息是 => " + message);
    }


    @RabbitListener(queues = MQField.HELLO_GOODS_QUEUE)
    @RabbitHandler
    public void process(Teacher teacher) {
        System.out.println("------ 接收实体对象 ------");
        System.out.println("HelloReceiver接收到的实体对象是 => " + Arrays.toString(SerializeUtil.serialize(teacher)));
    }

}
