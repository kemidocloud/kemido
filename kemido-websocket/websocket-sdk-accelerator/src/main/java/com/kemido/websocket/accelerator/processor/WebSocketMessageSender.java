package com.kemido.websocket.accelerator.processor;

import com.kemido.websocket.accelerator.domain.WebSocketChannel;
import com.kemido.websocket.accelerator.domain.WebSocketMessage;
import com.kemido.websocket.accelerator.exception.IllegalChannelException;
import com.kemido.websocket.accelerator.exception.PrincipalNotFoundException;
import com.kemido.websocket.accelerator.properties.WebSocketProperties;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;

/**
 * <p>Description: Web Socket 服务端消息发送 </p>
 */
public class WebSocketMessageSender {

    private static final Logger log = LoggerFactory.getLogger(WebSocketMessageSender.class);

    private SimpMessagingTemplate simpMessagingTemplate;
    private SimpUserRegistry simpUserRegistry;
    private WebSocketProperties webSocketProperties;

    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void setSimpUserRegistry(SimpUserRegistry simpUserRegistry) {
        this.simpUserRegistry = simpUserRegistry;
    }

    public void setWebSocketProperties(WebSocketProperties webSocketProperties) {
        this.webSocketProperties = webSocketProperties;
    }

    /**
     * 发送给指定用户信息。
     *
     * @param webSocketMessage 发送内容参数实体 {@link WebSocketMessage}
     * @param <T>              指定 payload 类型
     * @throws IllegalChannelException    Web Socket 通道设置错误
     * @throws PrincipalNotFoundException 该服务中无法找到与 identity 对应的用户 Principal
     */
    public <T> void toUser(WebSocketMessage<T> webSocketMessage) throws IllegalChannelException, PrincipalNotFoundException {
        WebSocketChannel webSocketChannel = WebSocketChannel.getWebSocketChannel(webSocketMessage.getChannel());
        if (ObjectUtils.isEmpty(webSocketChannel)) {
            throw new IllegalChannelException("Web socket channel is incorrect!");
        }

        SimpUser simpUser = simpUserRegistry.getUser(webSocketMessage.getTo());
        if (ObjectUtils.isEmpty(simpUser)) {
            throw new PrincipalNotFoundException("Web socket user principal is not found!");
        }

        log.debug("[Kemido] |- Web socket send message to user [{}].", webSocketMessage.getTo());
        simpMessagingTemplate.convertAndSendToUser(webSocketMessage.getTo(), webSocketChannel.getDestination(), webSocketMessage.getPayload());
    }

    /**
     * 广播 WebSocket 信息
     *
     * @param payload 发送的内容
     * @param <T>     payload 类型
     */
    public <T> void toAll(T payload) {
        simpMessagingTemplate.convertAndSend(webSocketProperties.getBroadcast(), payload);
    }
}
