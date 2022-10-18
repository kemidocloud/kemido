package com.kemido.oauth2.compliance.listener;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.oauth2.compliance.service.OAuth2AccountStatusService;
import com.kemido.oauth2.core.constants.OAuth2Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.nio.charset.StandardCharsets;

/**
 * <p>Description: 账户锁定状态监听 </p>
 */
public class AccountStatusListener extends KeyExpirationEventMessageListener {

    private static final Logger log = LoggerFactory.getLogger(AccountStatusListener.class);

    private final OAuth2AccountStatusService accountLockService;

    public AccountStatusListener(RedisMessageListenerContainer listenerContainer, OAuth2AccountStatusService accountLockService) {
        super(listenerContainer);
        this.accountLockService = accountLockService;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = new String(message.getBody(),StandardCharsets.UTF_8);
        if (StringUtils.contains(key, OAuth2Constants.CACHE_NAME_TOKEN_LOCKED_USER_DETAIL)) {
            String userId = StringUtils.substringAfterLast(key, SymbolConstants.COLON);
            log.info("[Kemido] |- Parse the user [{}] at expired redis cache key [{}]", userId, key);
            if (StringUtils.isNotBlank(userId)) {
                log.debug("[Kemido] |- Automatically unlock user account [{}]", userId) ;
                accountLockService.enable(userId);
            }
        }
    }
}
