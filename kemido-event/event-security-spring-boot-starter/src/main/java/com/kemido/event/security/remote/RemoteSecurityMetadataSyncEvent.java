package com.kemido.event.security.remote;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * <p>Description: Security Metadata 远程刷新事件 </p>
 */
public class RemoteSecurityMetadataSyncEvent extends RemoteApplicationEvent {

    private String data;

    public RemoteSecurityMetadataSyncEvent() {
        super();
    }

    public RemoteSecurityMetadataSyncEvent(String data, String originService, String destinationService) {
        super(data, originService, DEFAULT_DESTINATION_FACTORY.getDestination(destinationService));
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
