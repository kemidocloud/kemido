package com.kemido.event.security.remote;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * <p>Description: 修改用户状态远程事件 </p>
 */
public class RemoteChangeUserStatusEvent extends RemoteApplicationEvent {

    private String data;

    public RemoteChangeUserStatusEvent() {
        super();
    }

    public RemoteChangeUserStatusEvent(String data, String originService, String destinationService) {
        super(data, originService, DEFAULT_DESTINATION_FACTORY.getDestination(destinationService));
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
