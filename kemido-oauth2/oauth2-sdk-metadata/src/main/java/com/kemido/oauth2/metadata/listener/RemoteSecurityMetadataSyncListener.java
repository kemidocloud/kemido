package com.kemido.oauth2.metadata.listener;

import com.kemido.assistant.core.json.jackson2.utils.JacksonUtils;
import com.kemido.event.security.remote.RemoteSecurityMetadataSyncEvent;
import com.kemido.oauth2.core.definition.domain.SecurityAttribute;
import com.kemido.oauth2.metadata.processor.SecurityMetadataAnalysisProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Description: Security Metadata 数据同步监听 </p>
 */
@Component
public class RemoteSecurityMetadataSyncListener implements ApplicationListener<RemoteSecurityMetadataSyncEvent> {

    private static final Logger log = LoggerFactory.getLogger(RemoteSecurityMetadataSyncListener.class);

    private final SecurityMetadataAnalysisProcessor securityMetadataAnalysisProcessor;
    private final ServiceMatcher serviceMatcher;

    public RemoteSecurityMetadataSyncListener(SecurityMetadataAnalysisProcessor securityMetadataAnalysisProcessor, ServiceMatcher serviceMatcher) {
        this.securityMetadataAnalysisProcessor = securityMetadataAnalysisProcessor;
        this.serviceMatcher = serviceMatcher;
    }

    @Override
    public void onApplicationEvent(RemoteSecurityMetadataSyncEvent event) {

        if (!serviceMatcher.isFromSelf(event)) {
            log.info("[Kemido] |- Remote security metadata sync listener, response event!");

            String data = event.getData();
            if (StringUtils.isNotBlank(data)) {
                List<SecurityAttribute> securityAttributes = JacksonUtils.toList(data, SecurityAttribute.class);

                if (CollectionUtils.isNotEmpty(securityAttributes)) {
                    log.debug("[Kemido] |- Got security attributes from service [{}], current [{}] start to process security attributes.", event.getOriginService(), event.getDestinationService());
                    securityMetadataAnalysisProcessor.processSecurityMetadata(securityAttributes);
                }
            }
        }
    }
}
