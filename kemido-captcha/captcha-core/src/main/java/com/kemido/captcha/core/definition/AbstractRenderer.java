package com.kemido.captcha.core.definition;

import com.kemido.cache.jetcache.stamp.AbstractStampManager;
import com.kemido.captcha.core.properties.CaptchaProperties;
import com.kemido.captcha.core.provider.ResourceProvider;
import cn.hutool.core.img.ImgUtil;
import com.alicp.jetcache.anno.CacheType;

import java.awt.image.BufferedImage;
import java.time.Duration;

/**
 * <p>Description: 基础绘制器 </p>
 */
public abstract class AbstractRenderer<K, V> extends AbstractStampManager<K, V> implements Renderer {

    protected static final String BASE64_PNG_IMAGE_PREFIX = "data:image/png;base64,";
    protected static final String BASE64_GIF_IMAGE_PREFIX = "data:image/gif;base64,";

    private ResourceProvider resourceProvider;

    public AbstractRenderer(String cacheName) {
        super(cacheName);
    }

    public AbstractRenderer(String cacheName, CacheType cacheType) {
        super(cacheName, cacheType);
    }

    public AbstractRenderer(String cacheName, CacheType cacheType, Duration expire) {
        super(cacheName, cacheType, expire);
    }

    public void setResourceProvider(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    public ResourceProvider getResourceProvider() {
        return resourceProvider;
    }

    protected CaptchaProperties getCaptchaProperties() {
        return getResourceProvider().getCaptchaProperties();
    }

    protected String getBase64ImagePrefix() {
        return BASE64_PNG_IMAGE_PREFIX;
    }

    protected String toBase64(BufferedImage bufferedImage) {
        String image = ImgUtil.toBase64(bufferedImage, ImgUtil.IMAGE_TYPE_PNG);
        return getBase64ImagePrefix() + image;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
