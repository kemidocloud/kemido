package com.kemido.oauth2.compliance.stamp;

import com.kemido.cache.jetcache.stamp.AbstractStampManager;
import com.kemido.oauth2.core.constants.OAuth2Constants;
import com.kemido.oauth2.core.properties.OAuth2ComplianceProperties;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 锁定账户签章管理 </p>
 */
@Component
public class LockedUserDetailsStampManager extends AbstractStampManager<String, String> {

    private final OAuth2ComplianceProperties complianceProperties;

    @Autowired
    public LockedUserDetailsStampManager(OAuth2ComplianceProperties complianceProperties) {
        super(OAuth2Constants.CACHE_NAME_TOKEN_LOCKED_USER_DETAIL);
        this.complianceProperties = complianceProperties;
    }

    @Override
    public String nextStamp(String key) {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(complianceProperties.getSignInFailureLimited().getExpire());
    }
}
