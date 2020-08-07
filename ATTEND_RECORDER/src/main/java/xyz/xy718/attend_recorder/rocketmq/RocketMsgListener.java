package xyz.xy718.attend_recorder.rocketmq;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.List;

/**
 * 签到消息监听
 * @author: Xy718
 * @date: 2020/8/4 17:42
 * @description:
 */
@Component
public class RocketMsgListener implements MessageListenerConcurrently{
    private static final Logger logger = LoggerFactory.getLogger(RocketMsgListener.class) ;

    @Value("${rocket.topic}")
    public String rocketTopic ;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(
            List<MessageExt> list
            , ConsumeConcurrentlyContext consumeConcurrentlyContext
    ) {
        if (CollectionUtils.isEmpty(list)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        logger.info("签到人为："+new String(messageExt.getBody()));
        int reConsume = messageExt.getReconsumeTimes();
        // 消息已经重试了3次，如果不需要再次消费，则返回成功
        if(reConsume >=3){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

        if(messageExt.getTopic().equals(rocketTopic)){
            String tags = messageExt.getTags() ;
            switch (tags){
                case "rocketTag":
                    logger.info("开户 tag == >>"+tags);
                    break ;
                default:
                    logger.info("未匹配到Tag == >>"+tags);
                    break;
            }
        }
        // 消息消费成功
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
