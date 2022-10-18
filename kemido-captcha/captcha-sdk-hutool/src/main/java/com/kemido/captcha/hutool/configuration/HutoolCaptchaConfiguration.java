package com.kemido.captcha.hutool.configuration;

import com.kemido.captcha.core.definition.enums.CaptchaCategory;
import com.kemido.captcha.core.provider.ResourceProvider;
import com.kemido.captcha.hutool.renderer.CircleCaptchaRenderer;
import com.kemido.captcha.hutool.renderer.GifCaptchaRenderer;
import com.kemido.captcha.hutool.renderer.LineCaptchaRenderer;
import com.kemido.captcha.hutool.renderer.ShearCaptchaRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Hutool 验证码配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class HutoolCaptchaConfiguration {

    private static final Logger log = LoggerFactory.getLogger(HutoolCaptchaConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Captcha Hutool] Auto Configure.");
    }

    @Bean(CaptchaCategory.HUTOOL_LINE_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public LineCaptchaRenderer lineCaptchaRenderer(ResourceProvider resourceProvider) {
        LineCaptchaRenderer lineCaptchaRenderer = new LineCaptchaRenderer();
        lineCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Kemido] |- Bean [Hutool Line Captcha Renderer] Auto Configure.");
        return lineCaptchaRenderer;
    }

    @Bean(CaptchaCategory.HUTOOL_CIRCLE_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public CircleCaptchaRenderer circleCaptchaRenderer(ResourceProvider resourceProvider) {
        CircleCaptchaRenderer circleCaptchaRenderer = new CircleCaptchaRenderer();
        circleCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Kemido] |- Bean [Hutool Circle Captcha Renderer] Auto Configure.");
        return circleCaptchaRenderer;
    }

    @Bean(CaptchaCategory.HUTOOL_SHEAR_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public ShearCaptchaRenderer shearCaptchaRenderer(ResourceProvider resourceProvider) {
        ShearCaptchaRenderer shearCaptchaRenderer = new ShearCaptchaRenderer();
        shearCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Kemido] |- Bean [Hutool Shear Captcha Renderer] Auto Configure.");
        return shearCaptchaRenderer;
    }

    @Bean(CaptchaCategory.HUTOOL_GIF_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public GifCaptchaRenderer gifCaptchaRenderer(ResourceProvider resourceProvider) {
        GifCaptchaRenderer gifCaptchaRenderer = new GifCaptchaRenderer();
        gifCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Kemido] |- Bean [Hutool Gif Captcha Renderer] Auto Configure.");
        return gifCaptchaRenderer;
    }
}
