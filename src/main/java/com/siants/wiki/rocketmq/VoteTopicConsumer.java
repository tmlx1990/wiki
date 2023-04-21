package com.siants.wiki.rocketmq;

import com.siants.wiki.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@RocketMQMessageListener(topic = "VOTE_TOPIC", consumerGroup = "default")
@Slf4j
public class VoteTopicConsumer implements RocketMQListener<MessageExt> {

    @Resource
    public WebSocketServer webSocketServer;
    @Override
    public void onMessage(MessageExt messageExt) {
        byte[] body = messageExt.getBody();
        log.info("ROCKETMQ收到消息 : {}", new String(body));
        webSocketServer.sendInfo(new String(body));
    }
}
