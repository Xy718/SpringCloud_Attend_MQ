# SpringCloud_Attend_MQ
一个基于分布式技术与消息中间件的签到业务削峰实战

具体业务解析请看博客：
 - [`[架构设计]有关线下多台机器并发上传签到数据的设计架构[RocketMQ](一)`](https://xy718.xyz/archives/2020073117)

项目结构:
```
SpringCloud_Attend_MQ
  服务项目         服务名                  端口
├ EUREKA_SERVER   注册中心              11800-11802
├ GATEWAY         API网关               12810-12819
└ PRE_ATTEND      签到打卡预处理服务     13100-13109
```