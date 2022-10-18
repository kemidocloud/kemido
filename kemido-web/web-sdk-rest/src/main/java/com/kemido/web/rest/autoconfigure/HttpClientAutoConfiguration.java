package com.kemido.web.rest.autoconfigure;

import com.kemido.web.rest.annotation.ConditionalOnFeignUseHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientConnectionManagerFactory;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientFactory;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>Description: HttpClient 自动配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnFeignUseHttpClient
public class HttpClientAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OkHttpAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Web HttpClient] Auto Configure.");
    }

    private final Timer connectionManagerTimer = new Timer(
            "FeignApacheHttpClientConfiguration.connectionManagerTimer", true);

    @Autowired(required = false)
    private RegistryBuilder registryBuilder;

    private CloseableHttpClient httpClient;

    @Bean
    @ConditionalOnMissingBean(HttpClientConnectionManager.class)
    public HttpClientConnectionManager connectionManager(ApacheHttpClientConnectionManagerFactory connectionManagerFactory, FeignHttpClientProperties feignHttpClientProperties) {

        final HttpClientConnectionManager connectionManager = connectionManagerFactory.newConnectionManager(
                feignHttpClientProperties.isDisableSslValidation(), feignHttpClientProperties.getMaxConnections(),
                feignHttpClientProperties.getMaxConnectionsPerRoute(), feignHttpClientProperties.getTimeToLive(),
                feignHttpClientProperties.getTimeToLiveUnit(), this.registryBuilder);
        this.connectionManagerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                connectionManager.closeExpiredConnections();
            }
        }, 30000, feignHttpClientProperties.getConnectionTimerRepeat());
        return connectionManager;
    }

    @Bean
    public CloseableHttpClient httpClient(ApacheHttpClientFactory httpClientFactory, HttpClientConnectionManager httpClientConnectionManager, FeignHttpClientProperties feignHttpClientProperties) {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(feignHttpClientProperties.getConnectionTimeout())
                .setRedirectsEnabled(feignHttpClientProperties.isFollowRedirects()).build();
        this.httpClient = httpClientFactory.createBuilder().setConnectionManager(httpClientConnectionManager)
                .setDefaultRequestConfig(defaultRequestConfig).build();
        return this.httpClient;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(CloseableHttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        log.trace("[Kemido] |- Bean [Client Http Request Factory for HttpClient] Auto Configure.");
        return httpComponentsClientHttpRequestFactory;
    }

    @PreDestroy
    public void destroy() {
        this.connectionManagerTimer.cancel();
        if (this.httpClient != null) {
            try {
                this.httpClient.close();
            } catch (IOException e) {
                log.trace("[Kemido] |- Could not correctly close httpClient.");
            }
        }
    }
}
