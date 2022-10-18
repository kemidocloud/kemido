package com.kemido.cache.redis.session;

import cn.hutool.core.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpCookie;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.session.CookieWebSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: Redis Web Session 扩展 </p>
 * <p>
 * 覆盖webSession中读取sessionId的写法，将SESSION信息进行base64解码，默认实现中是没有base64解码的，sessionId传到下游时不一致，会导致session不共享：
 */
public class KemidoCookieWebSessionIdResolver extends CookieWebSessionIdResolver {

    private static final Logger log = LoggerFactory.getLogger(KemidoCookieWebSessionIdResolver.class);

    /**
     * 处理 session id，进行解密，防止前后端处理不一致。
     * <p>
     * {@link org.springframework.session.web.http.DefaultCookieSerializer#readCookieValues(HttpServletRequest)}
     *
     * @param exchange Webflux Content
     * @return Cookie 内容
     */
    @Override
    public List<String> resolveSessionIds(ServerWebExchange exchange) {
        MultiValueMap<String, HttpCookie> cookieMap = exchange.getRequest().getCookies();
        // 获取SESSION
        List<HttpCookie> cookies = cookieMap.get(getCookieName());
        if (cookies == null) {
            return Collections.emptyList();
        }
        return cookies.stream().map(HttpCookie::getValue).map(this::base64Decode).collect(Collectors.toList());
    }

    private String base64Decode(String base64Value) {
        String result = Base64.decodeStr(base64Value);
        log.debug("[Kemido] |- Webflux decode session id to: [{}]" + result);
        return result;
    }
}
