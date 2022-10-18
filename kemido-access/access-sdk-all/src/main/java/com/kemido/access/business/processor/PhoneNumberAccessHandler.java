package com.kemido.access.business.processor;

import com.kemido.access.core.definition.AccessHandler;
import com.kemido.access.core.definition.AccessResponse;
import com.kemido.access.core.definition.AccessUserDetails;
import com.kemido.access.core.exception.AccessIdentityVerificationFailedException;
import com.kemido.assistant.core.constants.BaseConstants;
import com.kemido.assistant.core.domain.AccessPrincipal;
import com.kemido.sms.all.processor.SmsSendStrategyFactory;
import com.kemido.sms.all.stamp.VerificationCodeStampManager;
import com.kemido.sms.core.domain.Template;
import com.google.common.collect.ImmutableMap;

/**
 * <p>Description: 手机短信接入处理器 </p>
 */
public class PhoneNumberAccessHandler implements AccessHandler {

    private final VerificationCodeStampManager verificationCodeStampManager;
    private final SmsSendStrategyFactory smsSendStrategyFactory;

    public PhoneNumberAccessHandler(VerificationCodeStampManager verificationCodeStampManager, SmsSendStrategyFactory smsSendStrategyFactory) {
        this.verificationCodeStampManager = verificationCodeStampManager;
        this.smsSendStrategyFactory = smsSendStrategyFactory;
    }

    @Override
    public AccessResponse preProcess(String core, String... params) {
        String code = verificationCodeStampManager.create(core);
        boolean result;
        if (verificationCodeStampManager.getSandbox()) {
            result = true;
        } else {
            Template template = new Template();
            template.setType(verificationCodeStampManager.getVerificationCodeTemplateId());
            template.setParams(ImmutableMap.of(BaseConstants.CODE, code));
            result = smsSendStrategyFactory.send(template, core);
        }

        AccessResponse accessResponse = new AccessResponse();
        accessResponse.setSuccess(result);
        return accessResponse;
    }

    @Override
    public AccessUserDetails loadUserDetails(String source, AccessPrincipal accessPrincipal) {
        boolean isCodeOk = verificationCodeStampManager.check(accessPrincipal.getMobile(), accessPrincipal.getCode());
        if (isCodeOk) {
            AccessUserDetails accessUserDetails = new AccessUserDetails();
            accessUserDetails.setUuid(accessPrincipal.getMobile());
            accessUserDetails.setPhoneNumber(accessPrincipal.getMobile());
            accessUserDetails.setUserName(accessPrincipal.getMobile());
            accessUserDetails.setSource(source);

            verificationCodeStampManager.delete(accessPrincipal.getMobile());
            return accessUserDetails;
        }

        throw new AccessIdentityVerificationFailedException("Phone Verification Code Error!");
    }
}
