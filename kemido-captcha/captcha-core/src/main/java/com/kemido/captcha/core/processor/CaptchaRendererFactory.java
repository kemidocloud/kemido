package com.kemido.captcha.core.processor;

import com.kemido.captcha.core.definition.Renderer;
import com.kemido.captcha.core.definition.enums.CaptchaCategory;
import com.kemido.captcha.core.dto.Captcha;
import com.kemido.captcha.core.dto.Verification;
import com.kemido.captcha.core.exception.CaptchaCategoryIsIncorrectException;
import com.kemido.captcha.core.exception.CaptchaHandlerNotExistException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Description: Captcha 工厂 </p>
 */
@Component
public class CaptchaRendererFactory {

    @Autowired
    private final Map<String, Renderer> handlers = new ConcurrentHashMap<>(8);

    public Renderer getRenderer(String category) {
        CaptchaCategory captchaCategory = CaptchaCategory.getCaptchaCategory(category);

        if (ObjectUtils.isEmpty(captchaCategory)) {
            throw new CaptchaCategoryIsIncorrectException("Captcha category is incorrect.");
        }

        Renderer renderer = handlers.get(captchaCategory.getConstant());
        if (ObjectUtils.isEmpty(renderer)) {
            throw new CaptchaHandlerNotExistException();
        }

        return renderer;
    }

    public Captcha getCaptcha(String identity, String category) {
        Renderer renderer = getRenderer(category);
        return renderer.getCapcha(identity);
    }

    public boolean verify(Verification verification) {
        Renderer renderer = getRenderer(verification.getCategory());
        return renderer.verify(verification);
    }
}
