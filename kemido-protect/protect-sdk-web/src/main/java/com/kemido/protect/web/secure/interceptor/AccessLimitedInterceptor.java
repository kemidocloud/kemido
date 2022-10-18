package com.kemido.protect.web.secure.interceptor;

import com.kemido.protect.core.annotation.AccessLimited;
import com.kemido.protect.core.exception.FrequentRequestsException;
import com.kemido.protect.web.secure.stamp.AccessLimitedStampManager;
import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.format.DateTimeParseException;

/**
 * <p>Description: 访问防刷拦截器 </p>
 */
public class AccessLimitedInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AccessLimitedInterceptor.class);

    private AccessLimitedStampManager accessLimitedStampManager;

    public void setAccessLimitedStampManager(AccessLimitedStampManager accessLimitedStampManager) {
        this.accessLimitedStampManager = accessLimitedStampManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.trace("[Kemido] |- AccessLimitedInterceptor preHandle postProcess.");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        AccessLimited accessLimited = method.getAnnotation(AccessLimited.class);
        if (ObjectUtils.isNotEmpty(accessLimited)) {

            int maxTimes = accessLimitedStampManager.getSecureProperties().getAccessLimited().getMaxTimes();
            Duration expireDuration = Duration.ZERO;

            int annotationMaxTimes = accessLimited.maxTimes();
            if(annotationMaxTimes != 0) {
                maxTimes = annotationMaxTimes;
            }

            String annotationDuration = accessLimited.duration();
            if (StringUtils.isNotBlank(annotationDuration)) {
                try {
                    expireDuration = Duration.parse(annotationDuration);
                } catch (DateTimeParseException e) {
                    log.warn("[Kemido] |- AccessLimited duration value is incorrect, on api [{}].", request.getRequestURI());
                }
            }

            String flag = handlerMethod.toString();
            log.debug("[Kemido] |- AccessLimitedInterceptor process for request [{}].", flag);

            String key = SecureUtil.md5(flag);
            String expireKey = key + "_expire";
            Long times = accessLimitedStampManager.get(key);

            if (ObjectUtils.isEmpty(times) || times == 0L) {
                if (!expireDuration.isZero()) {
                    // 如果注解上配置了Duration且没有配置错可以正常解析，那么使用注解上的配置值
                    accessLimitedStampManager.create(key, expireDuration);
                    accessLimitedStampManager.put(expireKey, System.currentTimeMillis(), expireDuration);
                } else {
                    // 如果注解上没有配置Duration或者配置错无法正常解析，那么使用StampProperties的配置值
                    accessLimitedStampManager.create(key);
                    accessLimitedStampManager.put(expireKey, System.currentTimeMillis());
                }
                return true;
            } else {
                log.debug("[Kemido] |- AccessLimitedInterceptor request [{}] times.", times);

                if (times <= maxTimes) {
                    Duration newDuration = calculateRemainingTime(expireDuration, expireKey);
                    // 不管是注解上配置Duration值还是StampProperties中配置的Duration值，是不会变的
                    // 所以第一次存入expireKey对应的System.currentTimeMillis()时间后，这个值也不应该变化。
                    // 因此，这里只更新访问次数的标记值
                    accessLimitedStampManager.put(key, times + 1L, newDuration);
                    return true;
                } else {
                    throw new FrequentRequestsException("Requests are too frequent. Please try again later!");
                }
            }
        }

        return true;
    }

    /**
     * 计算剩余过期时间
     *
     * 每次create或者put，缓存的过期时间都会被覆盖。（注意：Jetcache put 方法的参数名：expireAfterWrite）。
     * 因为Jetcache没有Redis的incr之类的方法，那么每次放入Times值，都会更新过期时间，实际操作下来是变相的延长了过期时间。
     *
     * @param configuredDuration 注解上配置的、且可以正常解析的Duration值
     * @param expireKey 时间标记存储Key值。
     * @return 还剩余的过期时间 {@link Duration}
     */
    private Duration calculateRemainingTime(Duration configuredDuration, String expireKey) {
        Long begin = accessLimitedStampManager.get(expireKey);
        Long current = System.currentTimeMillis();
        long interval = current - begin;

        log.debug("[Kemido] |- AccessLimitedInterceptor operation interval [{}] millis.", interval);

        Duration duration;
        if (!configuredDuration.isZero()) {
            duration = configuredDuration.minusMillis(interval);
        } else {
            duration = accessLimitedStampManager.getExpire().minusMillis(interval);
        }

        return duration;
    }
}
