package com.kemido.captcha.graphic.configuration;

import com.kemido.captcha.core.definition.enums.CaptchaCategory;
import com.kemido.captcha.core.provider.ResourceProvider;
import com.kemido.captcha.graphic.renderer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 图形验证码配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class GraphicCaptchaConfiguration {

    private static final Logger log = LoggerFactory.getLogger(GraphicCaptchaConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Captcha Graphic] Auto Configure.");
    }

    @Bean(CaptchaCategory.ARITHMETIC_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public ArithmeticCaptchaRenderer arithmeticCaptchaRenderer(ResourceProvider resourceProvider) {
        ArithmeticCaptchaRenderer arithmeticCaptchaRenderer = new ArithmeticCaptchaRenderer();
        arithmeticCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Kemido] |- Bean [Arithmetic Captcha Renderer] Auto Configure.");
        return arithmeticCaptchaRenderer;
    }

    @Bean(CaptchaCategory.CHINESE_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public ChineseCaptchaRenderer chineseCaptchaRenderer(ResourceProvider resourceProvider) {
        ChineseCaptchaRenderer chineseCaptchaRenderer = new ChineseCaptchaRenderer();
        chineseCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Kemido] |- Bean [Chinese Captcha Renderer] Auto Configure.");
        return chineseCaptchaRenderer;
    }

    @Bean(CaptchaCategory.CHINESE_GIF_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public ChineseGifCaptchaRenderer chineseGifCaptchaRenderer(ResourceProvider resourceProvider) {
        ChineseGifCaptchaRenderer chineseGifCaptchaRenderer = new ChineseGifCaptchaRenderer();
        chineseGifCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Kemido] |- Bean [Chinese Gif Captcha Renderer] Auto Configure.");
        return chineseGifCaptchaRenderer;
    }

    @Bean(CaptchaCategory.SPEC_GIF_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public SpecGifCaptchaRenderer specGifCaptchaRenderer(ResourceProvider resourceProvider) {
        SpecGifCaptchaRenderer specGifCaptchaRenderer = new SpecGifCaptchaRenderer();
        specGifCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Kemido] |- Bean [Spec Gif Captcha Renderer] Auto Configure.");
        return specGifCaptchaRenderer;
    }

    @Bean(CaptchaCategory.SPEC_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public SpecCaptchaRenderer specCaptchaRenderer(ResourceProvider resourceProvider) {
        SpecCaptchaRenderer specCaptchaRenderer = new SpecCaptchaRenderer();
        specCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[Kemido] |- Bean [Spec Captcha Renderer] Auto Configure.");
        return specCaptchaRenderer;
    }
}
