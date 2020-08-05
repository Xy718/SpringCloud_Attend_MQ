package xyz.xy718.pre_attend.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.xy718.ResultBean;
import xyz.xy718.pre_attend.service.RocketMQService;

import javax.annotation.Resource;

/**
 * @author: Xy718
 * @date: 2020/8/3 11:23
 * @description: 打卡签到预处理程序
 */
@RestController
@RequestMapping("/attend")
@Slf4j
public class PreAttendController {

    @Value("${server.port}")
    String port;
    @Resource
    RocketMQService rocketMQService;

    /**
     * 根据卡号提交记录
     * @return
     */
    @PostMapping("/code")
    public ResultBean attendOfCode(
        @RequestParam("code")String code
    ){
        log.info("签到："+code);
        String msg=code;
        SendResult sendResult = null;
        try {
            sendResult = rocketMQService.sendMsg(msg) ;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("签到失败，请重试"+e.getMessage());
        }
        return ResultBean.success(sendResult) ;
    }
}
