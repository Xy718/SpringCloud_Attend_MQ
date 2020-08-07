package xyz.xy718.attend_recorder.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import xyz.xy718.attend_recorder.service.AttendService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 签到消息监听
 * @author: Xy718
 * @date: 2020/8/4 17:42
 * @description:
 */
@Slf4j
@Component
public class RocketMsgListener implements MessageListenerConcurrently{

    @Value("${attend.topic}")
    public String rocketTopic ;
    @Resource
    AttendService attendService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(
            List<MessageExt> list
            , ConsumeConcurrentlyContext consumeConcurrentlyContext
    ) {
        //检测订阅列表是否为空
        if (CollectionUtils.isEmpty(list)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        //获取订阅得到的消息
        MessageExt messageExt = list.get(0);
        String msg=new String(messageExt.getBody());
        log.info("订阅到了{}个签到人：{}",list.size(),msg);

        //签到！
        Optional<Boolean> attend=attendService.goAttend(msg.split(":")[0],msg.split(":")[1]);
        if(attend.isPresent()){
            if(attend.get()){
                //存在，且通过
                log.info("同学：{}签到成功",msg);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        }

        int reConsume = messageExt.getReconsumeTimes();
        // 消息已经重试了3次，如果不需要再次消费，则返回成功
        if(reConsume >=3){
            log.info("消息消费上限");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        // 消息消费并没有成功
        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
    }
}
