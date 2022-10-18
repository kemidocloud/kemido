package com.kemido.access.wxmpp.processor;

import com.kemido.access.wxmpp.properties.WxmppProperties;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: 微信公众号核心服务 </p>
 */
public class WxmppProcessor implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(WxmppProcessor.class);

    private WxMpService wxMpService;

    private WxmppProperties wxmppProperties;
    private WxmppLogHandler wxmppLogHandler;
    private StringRedisTemplate stringRedisTemplate;

    public void setWxmppProperties(WxmppProperties wxmppProperties) {
        this.wxmppProperties = wxmppProperties;
    }

    public void setWxmppLogHandler(WxmppLogHandler wxmppLogHandler) {
        this.wxmppLogHandler = wxmppLogHandler;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 代码里 getConfigs()处报错的同学，请注意仔细阅读项目说明，你的IDE需要引入lombok插件！！！！
        final List<WxmppProperties.MpConfig> configs = this.wxmppProperties.getConfigs();
        if (configs == null) {
            throw new RuntimeException("大哥，拜托先看下项目首页的说明（readme文件），添加下相关配置，注意别配错了！");
        }

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setMultiConfigStorages(configs
                .stream().map(a -> {
                    WxMpDefaultConfigImpl configStorage;
                    if (this.wxmppProperties.isUseRedis()) {
                        final WxmppProperties.RedisConfig redisConfig = this.wxmppProperties.getRedis();
                        configStorage = new WxMpRedisConfigImpl(new RedisTemplateWxRedisOps(stringRedisTemplate), a.getAppId());
                    } else {
                        configStorage = new WxMpDefaultConfigImpl();
                    }

                    configStorage.setAppId(a.getAppId());
                    configStorage.setSecret(a.getSecret());
                    configStorage.setToken(a.getToken());
                    configStorage.setAesKey(a.getAesKey());
                    return configStorage;
                }).collect(Collectors.toMap(WxMpDefaultConfigImpl::getAppId, a -> a, (o, n) -> o)));

        log.info("[Kemido] |- Bean [Kemido Weixin Micro Message Public Platform] Auto Configure.");

        this.wxMpService = wxMpService;
    }

    public WxMpService getWxMpService() {
        if (ObjectUtils.isEmpty(this.wxMpService)) {
            throw new IllegalArgumentException(String.format("Cannot find the configuration for wechat official accounts, please check!"));
        }

        return wxMpService;
    }

    public WxMpMessageRouter getWxMpMessageRouter() {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(this.getWxMpService());
        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(this.wxmppLogHandler).next();
        return newRouter;
    }
}
