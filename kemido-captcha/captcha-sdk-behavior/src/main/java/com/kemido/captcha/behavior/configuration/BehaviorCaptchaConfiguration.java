package com.kemido.captcha.behavior.configuration;

import com.kemido.captcha.behavior.renderer.JigsawCaptchaRenderer;
import com.kemido.captcha.behavior.renderer.WordClickCaptchaRenderer;
import com.kemido.captcha.core.definition.enums.CaptchaCategory;
import com.kemido.captcha.core.provider.ResourceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 行为验证码配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class BehaviorCaptchaConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BehaviorCaptchaConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Captcha Behavior] Auto Configure.");
    }

    @Bean(CaptchaCategory.JIGSAW_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public JigsawCaptchaRenderer jigsawCaptchaRenderer(ResourceProvider resourceProvider) {
        JigsawCaptchaRenderer jigsawCaptchaRenderer = new JigsawCaptchaRenderer();
        jigsawCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Kemido] |- Bean [Jigsaw Captcha Renderer] Auto Configure.");
        return jigsawCaptchaRenderer;
    }

    @Bean(CaptchaCategory.WORD_CLICK_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public WordClickCaptchaRenderer wordClickCaptchaRenderer(ResourceProvider resourceProvider) {
        WordClickCaptchaRenderer wordClickCaptchaRenderer = new WordClickCaptchaRenderer();
        wordClickCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Kemido] |- Bean [Word Click Captcha Renderer] Auto Configure.");
        return wordClickCaptchaRenderer;
    }
}
