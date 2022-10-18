package com.kemido.event.core.definition;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * <p>Description: 自定义 Application Event 基础类 </p>
 */
public class LocalApplicationEvent<T> extends ApplicationEvent {

    private final T data;

    public LocalApplicationEvent(T data) {
        super(data);
        this.data = data;
    }

    public LocalApplicationEvent(T data, Clock clock) {
        super(data, clock);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
