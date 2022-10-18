package com.kemido.event.security.remote;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * <p>Description: Request Mapping 收集远程事件 </p>
 */
public class RemoteRequestMappingGatherEvent extends RemoteApplicationEvent {

    private String data;

    public RemoteRequestMappingGatherEvent() {
        super();
    }

    public RemoteRequestMappingGatherEvent(String data, String originService, String destinationService) {
        super(data, originService, DEFAULT_DESTINATION_FACTORY.getDestination(destinationService));
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
