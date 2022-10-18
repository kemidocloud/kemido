package com.kemido.event.pay.remote;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * <p>Description: 远程支付通知事件 </p>
 */
public class RemotePaymentNotifyEvent extends RemoteApplicationEvent {

    private String data;

    public RemotePaymentNotifyEvent() {
        super();
    }

    public RemotePaymentNotifyEvent(String data, String originService, String destinationService) {
        super(data, originService, DEFAULT_DESTINATION_FACTORY.getDestination(destinationService));
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
