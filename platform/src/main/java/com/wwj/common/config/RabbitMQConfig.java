package com.wwj.common.config;

import com.wwj.common.constants.MQField;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

/**
 * RabbitMQ消息队列配置类
 * <p>
 * 注意：如果已在配置文件中声明了Queue对象，就不用在RabbitMQ的管理员页面创建队列（Queue）了
 * @author 122439195@qq.com
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 声明接收字符串的队列 Hello 默认
     * @return Queue
     */
    @Bean
    public Queue stringQueue() {

        //boolean isDurable = true;//是否持久化
        //boolean isExclusive = false;  //仅创建者可以使用的私有队列，断开后自动删除
        //boolean isAutoDelete = false;  //当所有消费客户端连接断开后，是否自动删除队列
        //Queue queue = new Queue(MQField.HELLO_STRING_QUEUE, isDurable, isExclusive, isAutoDelete);
        //return  queue;

        //return new Queue(MQField.HELLO_STRING_QUEUE); //默认支持持久化

        return QueueBuilder.durable(MQField.HELLO_STRING_QUEUE)
                //.exclusive()
                //.autoDelete()
                .build();
    }

    /**
     * 声明接收Goods对象的队列 Hello  支持持久化
     * @return Queue
     */
    @Bean
    public Queue goodsQueue() {

        return QueueBuilder.durable(MQField.HELLO_GOODS_QUEUE).build();
    }

    /**
     * 声明WorkQueue队列 competing consumers pattern，多个消费者不会重复消费队列的相同消息
     * @return Queue
     */
    @Bean
    public Queue workQueue() {
        return QueueBuilder.durable(MQField.MY_WORKER_QUEUE).build();
    }

    /**
     * 消息队列中最常见的模式：发布订阅模式
     * <p>
     * 声明发布订阅模式队列 Publish/Subscribe
     * <p>
     * exchange类型包括：direct, topic, headers 和 fanout
     **/

    /*fanout（广播）队列相关声明开始*/
    @Bean
    public Queue fanOutAQueue() {
        return QueueBuilder.durable(MQField.MY_FANOUT_A_QUEUE).build();
    }

    @Bean
    public Queue fanOutBQueue() {
        return QueueBuilder.durable(MQField.MY_FANOUT_B_QUEUE).build();
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange(MQField.MY_FANOUT_EXCHANGE).build();
    }

    @Bean
    Binding bindingExchangeA(Queue fanOutAQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutAQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue fanOutBQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutBQueue).to(fanoutExchange);
    }

    /*fanout队列相关声明结束*/


    /*topic队列相关声明开始*/

    @Bean
    public Queue topicAQueue() {
        return QueueBuilder.durable(MQField.MY_TOPIC_A_QUEUE).build();
    }

    @Bean
    public Queue topicBQueue() {
        return QueueBuilder.durable(MQField.MY_TOPIC_B_QUEUE).build();
    }

    @Bean
    TopicExchange topicExchange() {
        return ExchangeBuilder.topicExchange(MQField.MY_TOPIC_EXCHANGE).build();
    }

    //绑定时，注意队列名称与上述方法名一致
    @Bean
    Binding bindingTopicAExchangeMessage(Queue topicAQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicAQueue).to(topicExchange).with(MQField.MY_TOPIC_ROUTINGKEY_A);
    }

    @Bean
    Binding bindingTopicBExchangeMessages(Queue topicBQueue, TopicExchange topicExchange) {

        return BindingBuilder.bind(topicBQueue).to(topicExchange).with(MQField.MY_TOPIC_ROUTINGKEY_B);

    }

    /*topic队列相关声明结束*/

    /*direct队列相关声明开始*/

    @Bean
    public Queue directAQueue() {
        return QueueBuilder.durable(MQField.MY_DIRECT_A_QUEUE).build();
    }

    @Bean
    public Queue directBQueue() {
        return QueueBuilder.durable(MQField.MY_DIRECT_B_QUEUE).build();
    }

    /**
     * 声明Direct交换机 支持持久化.
     *
     * @return the exchange
     */
    @Bean
    DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(MQField.MY_DIRECT_EXCHANGE).durable(true).build();
    }

    @Bean
    Binding bindingDirectAExchangeMessage(Queue directAQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directAQueue).to(directExchange).with(MQField.MY_DIRECT_ROUTINGKEY_A);
    }

    @Bean
    Binding bindingDirectBExchangeMessage(Queue directBQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directBQueue).to(directExchange)
                .with(MQField.MY_DIRECT_ROUTINGKEY_B);
    }

    /*direct队列相关声明结束*/
}
