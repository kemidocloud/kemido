package com.kemido.event.pay.remote;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * <p>Description: 远程支付返回事件 </p>
 */
public class RemotePaymentReturnEvent extends RemoteApplicationEvent {

    private String data;

    public RemotePaymentReturnEvent() {
        super();
    }

    public RemotePaymentReturnEvent(String data, String originService, String destinationService) {
        super(data, originService, DEFAULT_DESTINATION_FACTORY.getDestination(destinationService));
        this.data = data;
    }

    public String getData() {
        return data;
    }
}