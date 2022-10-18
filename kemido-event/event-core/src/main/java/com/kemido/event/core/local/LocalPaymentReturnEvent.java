package com.kemido.event.core.local;

import com.kemido.event.core.definition.LocalApplicationEvent;

import java.time.Clock;
import java.util.Map;

/**
 * <p>Description: 单体模式下本地返回事件 </p>
 */
public class LocalPaymentReturnEvent extends LocalApplicationEvent<Map<String, String>> {

    public LocalPaymentReturnEvent(Map<String, String> data) {
        super(data);
    }

    public LocalPaymentReturnEvent(Map<String, String> data, Clock clock) {
        super(data, clock);
    }
}
