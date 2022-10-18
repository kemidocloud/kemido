package com.kemido.event.core.local;

import com.kemido.event.core.definition.LocalApplicationEvent;
import com.kemido.web.core.domain.UserStatus;

import java.time.Clock;

/**
 * <p>Description: 本地用户状态变更事件 </p>
 */
public class LocalChangeUserStatusEvent extends LocalApplicationEvent<UserStatus> {

    public LocalChangeUserStatusEvent(UserStatus data) {
        super(data);
    }

    public LocalChangeUserStatusEvent(UserStatus data, Clock clock) {
        super(data, clock);
    }
}
