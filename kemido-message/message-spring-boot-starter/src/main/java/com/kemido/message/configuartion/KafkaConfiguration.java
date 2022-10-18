package com.kemido.message.configuartion;

import com.kemido.message.core.annotation.ConditionalOnKafkaEnabled;
import com.kemido.message.core.properties.QueueProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Kafka 配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnKafkaEnabled
public class KafkaConfiguration {

    private static final Logger log = LoggerFactory.getLogger(KafkaConfiguration.class);

    @Autowired
    private QueueProperties queueProperties;
    @Autowired
    private ConsumerFactory<String, String> consumerFactory;

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Message Kafka] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean(ConcurrentKafkaListenerContainerFactory.class)
    public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        concurrentKafkaListenerContainerFactory.setAutoStartup(queueProperties.getKafka().getEnabled());
        log.trace("[Kemido] |- Bean [Concurrent Kafka Listener ContainerFactory] Auto Configure.");
        return concurrentKafkaListenerContainerFactory;
    }
}
