package xyz.xy718.pre_attend.service;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Xy718
 * @date: 2020/8/4 16:30
 * @description:
 */
@Service
public class RocketMQService {
    @Value("${rocket.group}")
    public String rocketGroup ;
    @Value("${rocket.topic}")
    public String rocketTopic ;
    @Value("${rocket.tag}")
    public String rocketTag ;

    @Resource
    private DefaultMQProducer defaultMQProducer;

    public SendResult sendMsg(String msgInfo) {
        // 可以不使用Config中的Group
        defaultMQProducer.setProducerGroup(rocketGroup);
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message(rocketTopic,
                    rocketTag,
                    "open_account_key", msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult ;
    }
}
