package xyz.xy718.pre_attend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Xy718
 * @date: 2020/8/3 11:23
 * @description: 打卡签到预处理程序
 */
@RestController
@RequestMapping("/attend")
public class PreAttendController {

    @Value("${server.port}")
    String port;
    /**
     * 根据卡号提交记录
     */
    @PostMapping("/code")
    public String attendOfCode(
        @RequestParam("code")String code
    ){
        return "1 "+code+":"+port;
    }
}
