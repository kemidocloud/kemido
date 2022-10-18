package com.kemido.websocket.accelerator.processor;

import com.kemido.websocket.accelerator.domain.WebSocketMessage;
import com.kemido.websocket.accelerator.exception.IllegalChannelException;
import com.kemido.websocket.accelerator.exception.PrincipalNotFoundException;
import com.kemido.websocket.accelerator.properties.WebSocketProperties;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.codec.JsonJacksonCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * <p>Description: WebSocket集群处理器 </p>
 */
public class WebSocketClusterProcessor implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(WebSocketClusterProcessor.class);

    private RedissonClient redissonClient;
    private WebSocketProperties webSocketProperties;
    private WebSocketMessageSender webSocketMessageSender;

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void setWebSocketProperties(WebSocketProperties webSocketProperties) {
        this.webSocketProperties = webSocketProperties;
    }

    public void setWebSocketMessageSender(WebSocketMessageSender webSocketMessageSender) {
        this.webSocketMessageSender = webSocketMessageSender;
    }

    /**
     * 发送给集群中指定用户信息。
     *
     * @param webSocketMessage 发送内容参数实体 {@link WebSocketMessage}
     */
    public void toClusterUser(WebSocketMessage<String> webSocketMessage) {
        try {
            webSocketMessageSender.toUser(webSocketMessage);
        } catch (PrincipalNotFoundException e) {
            RTopic rTopic = redissonClient.getTopic(webSocketProperties.getTopic(), new JsonJacksonCodec());
            rTopic.publish(webSocketMessage);
            log.debug("[Kemido] |- Current instance can not found user [{}], publish message.", webSocketMessage.getTo());
        } catch (IllegalChannelException e) {
            log.error("[Kemido] |- Web socket channel is incorrect.");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RTopic topic = redissonClient.getTopic(webSocketProperties.getTopic());
        topic.addListener(WebSocketMessage.class, (MessageListener<WebSocketMessage<String>>) (charSequence, webSocketMessage) -> {
            log.debug("[Kemido] |- Redisson received web socket sync message [{}]", webSocketMessage);
            webSocketMessageSender.toUser(webSocketMessage);
        });
    }
}
