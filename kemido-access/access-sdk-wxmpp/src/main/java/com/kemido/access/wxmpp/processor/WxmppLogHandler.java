package com.kemido.access.wxmpp.processor;

import com.kemido.assistant.core.json.jackson2.utils.JacksonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>Description: 微信公众号日志处理 </p>
 */
@Component
public class WxmppLogHandler implements WxMpMessageHandler {

    private static final Logger log = LoggerFactory.getLogger(WxmppLogHandler.class);

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        // 代码逻辑未实现,仅仅简单打印信息
        log.info("\n接收到请求消息，内容：{}", JacksonUtils.toJson(wxMpXmlMessage));
        return null;
    }
}
