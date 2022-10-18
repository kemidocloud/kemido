package com.kemido.sms.all.stamp;

import com.kemido.cache.jetcache.stamp.AbstractStampManager;
import com.kemido.sms.all.properties.SmsProperties;
import com.kemido.sms.core.constants.SmsConstants;
import cn.hutool.core.util.RandomUtil;

/**
 * <p>Description: 手机短信验证码签章 </p>
 */
public class VerificationCodeStampManager extends AbstractStampManager<String, String> {

    private SmsProperties smsProperties;

    public void setSmsProperties(SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
    }

    public VerificationCodeStampManager() {
        super(SmsConstants.CACHE_NAME_TOKEN_VERIFICATION_CODE);
    }

    @Override
    public String nextStamp(String key) {
        if (smsProperties.getSandbox()) {
            return smsProperties.getTestCode();
        } else {
            return RandomUtil.randomNumbers(smsProperties.getLength());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(smsProperties.getExpire());
    }

    public Boolean getSandbox() {
        return smsProperties.getSandbox();
    }

    public String getVerificationCodeTemplateId() {
        return smsProperties.getVerificationCodeTemplateId();
    }
}
