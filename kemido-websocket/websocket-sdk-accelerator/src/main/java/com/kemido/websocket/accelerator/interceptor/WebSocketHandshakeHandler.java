package com.kemido.websocket.accelerator.interceptor;

import com.kemido.assistant.core.constants.HttpHeaders;
import com.kemido.websocket.accelerator.domain.WebSocketPrincipal;
import com.kemido.websocket.accelerator.properties.WebSocketProperties;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

/**
 * <p>Description: 设置认证用户信息 </p>
 */
public class WebSocketHandshakeHandler extends DefaultHandshakeHandler {

    private static final Logger log = LoggerFactory.getLogger(WebSocketHandshakeHandler.class);

    private WebSocketProperties webSocketProperties;

    public void setWebSocketProperties(WebSocketProperties webSocketProperties) {
        this.webSocketProperties = webSocketProperties;
    }

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {

        Principal principal = request.getPrincipal();
        if (ObjectUtils.isNotEmpty(principal)) {
            log.debug("[Kemido] |- Get user principal from request, value is  [{}].", principal.getName());
            return principal;
        }

        Object user = null;
        HttpServletRequest httpServletRequest = getHttpServletRequest(request);
        if (ObjectUtils.isNotEmpty(httpServletRequest)) {
            user = httpServletRequest.getAttribute(webSocketProperties.getPrincipalAttribute());
            if (ObjectUtils.isEmpty(user)) {
                user = httpServletRequest.getParameter(webSocketProperties.getPrincipalAttribute());
                if (ObjectUtils.isEmpty(user)) {
                    user = httpServletRequest.getHeader(HttpHeaders.X_HERODOTUS_SESSION);
                } else {
                    log.debug("[Kemido] |- Get user principal [{}] from request parameter, use parameter  [{}]..", user, webSocketProperties.getPrincipalAttribute());
                }
            } else {
                log.debug("[Kemido] |- Get user principal [{}] from request attribute, use attribute  [{}]..", user, webSocketProperties.getPrincipalAttribute());
            }
        }

        if (ObjectUtils.isEmpty(user)) {
            HttpSession httpSession = getSession(request);
            if (ObjectUtils.isNotEmpty(httpSession)) {
                user = httpSession.getAttribute(webSocketProperties.getPrincipalAttribute());
                if (ObjectUtils.isEmpty(user)) {
                    user = httpSession.getId();
                } else {
                    log.debug("[Kemido] |- Get user principal [{}] from httpsession, use attribute  [{}].", user, webSocketProperties.getPrincipalAttribute());
                }
            } else {
                log.error("[Kemido] |- Cannot find session from websocket request.");
                return null;
            }
        } else {
            log.debug("[Kemido] |- Get user principal [{}] from request header X_HERODOTUS_SESSION.", user);
        }

        return new WebSocketPrincipal((String) user);
    }

    private HttpServletRequest getHttpServletRequest(ServerHttpRequest request) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            return serverRequest.getServletRequest();
        }

        return null;
    }

    private HttpSession getSession(ServerHttpRequest request) {
        HttpServletRequest httpServletRequest = getHttpServletRequest(request);
        if (ObjectUtils.isNotEmpty(httpServletRequest)) {
            return httpServletRequest.getSession(false);
        }
        return null;
    }
}
