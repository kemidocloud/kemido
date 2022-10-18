package com.kemido.oauth2.compliance.stamp;

import com.kemido.cache.jetcache.stamp.AbstractCountStampManager;
import com.kemido.oauth2.compliance.dto.SignInErrorStatus;
import com.kemido.oauth2.core.constants.OAuth2Constants;
import com.kemido.oauth2.core.properties.OAuth2ComplianceProperties;
import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 登录失败次数限制签章管理 </p>
 */
@Component
public class SignInFailureLimitedStampManager extends AbstractCountStampManager {

    private final OAuth2ComplianceProperties complianceProperties;

    @Autowired
    public SignInFailureLimitedStampManager(OAuth2ComplianceProperties complianceProperties) {
        super(OAuth2Constants.CACHE_NAME_TOKEN_SIGN_IN_FAILURE_LIMITED);
        this.complianceProperties = complianceProperties;
    }

    @Override
    public Long nextStamp(String key) {
        return 1L;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(complianceProperties.getSignInFailureLimited().getExpire());
    }

    public OAuth2ComplianceProperties getComplianceProperties() {
        return complianceProperties;
    }

    public SignInErrorStatus errorStatus(String username) {
        int maxTimes = complianceProperties.getSignInFailureLimited().getMaxTimes();
        Long storedTimes = get(SecureUtil.md5(username));

        int errorTimes = 0;
        if (ObjectUtils.isNotEmpty(storedTimes)) {
            errorTimes = storedTimes.intValue();
        }

        int remainTimes = maxTimes;
        if (errorTimes != 0) {
            remainTimes =  maxTimes - errorTimes;
        }

        boolean isLocked = false;
        if (errorTimes == maxTimes) {
            isLocked = true;
        }

        SignInErrorStatus status = new SignInErrorStatus();
        status.setErrorTimes(errorTimes);
        status.setRemainTimes(remainTimes);
        status.setLocked(isLocked);

        return status;
    }
}
