package com.kemido.event.core.definition;

import com.kemido.web.core.context.ServiceContext;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

/**
 * <p>Description: 抽象 JPA 实体变更 Listener</p>
 */
public abstract class AbstractEntityListener implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    protected ApplicationContext getApplicationContext() {
        if (ObjectUtils.isEmpty(applicationContext)) {
            return ServiceContext.getInstance().getApplicationContext();
        }
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected void publishEvent(ApplicationEvent event) {
        this.getApplicationContext().publishEvent(event);
    }
}
