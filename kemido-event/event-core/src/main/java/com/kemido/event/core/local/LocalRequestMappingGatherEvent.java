package com.kemido.event.core.local;

import com.kemido.event.core.definition.LocalApplicationEvent;
import com.kemido.web.core.domain.RequestMapping;

import java.time.Clock;
import java.util.List;

/**
 * <p>Description: 本地RequestMapping收集事件 </p>
 */
public class LocalRequestMappingGatherEvent extends LocalApplicationEvent<List<RequestMapping>> {

    public LocalRequestMappingGatherEvent(List<RequestMapping> data) {
        super(data);
    }

    public LocalRequestMappingGatherEvent(List<RequestMapping> data, Clock clock) {
        super(data, clock);
    }
}
