package com.kemido.message.core.properties;

import com.kemido.message.core.constants.MessageConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 消息队列配置 </p>
 */
@ConfigurationProperties(prefix = MessageConstants.PROPERTY_PREFIX_QUEUE)
public class QueueProperties {

    private Kafka kafka = new Kafka();

    public Kafka getKafka() {
        return kafka;
    }

    public void setKafka(Kafka kafka) {
        this.kafka = kafka;
    }

    public static class Kafka {

        /**
         * Kakfa监听是否自动启动
         */
        private Boolean enabled = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }
}
