package com.kemido.web.rest.autoconfigure;

import com.kemido.web.rest.annotation.ConditionalOnFeignUseOkHttp;
import com.kemido.web.rest.enhance.OkHttpResponseInterceptor;
import okhttp3.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.commons.httpclient.OkHttpClientConnectionPoolFactory;
import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.loadbalancer.FeignLoadBalancerAutoConfiguration;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: OkHttp 自动配置 </p>
 * <p>
 * 1. 默认让 Feign 使用 OkHttp 作为 HttpClient。所以直接使用 Feign 的配置来对 OkHttp 进行配置。
 * 2. 如果存在 `feign.okhttp.enabled` 配置， 同时其值为 `true`，就会自动配置 OkHttp。
 * 3. 在此处配置 OkHttp，也是为了共用 OkHttp 的配置，让其可以同时支持 RestTemplate
 * <p>
 * {@code org.springframework.cloud.openfeign.loadbalancer.OkHttpFeignLoadBalancerConfiguration}
 *
 * @see <a href='http://leejoker.github.io/post/feign%E4%BD%BF%E7%94%A8okhttp3%E7%9A%84%E6%AD%A3%E7%A1%AE%E5%A7%BF%E5%8A%BF/'> 参考资料</a>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnFeignUseOkHttp
@AutoConfigureBefore(FeignLoadBalancerAutoConfiguration.class)
public class OkHttpAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OkHttpAutoConfiguration.class);

    private okhttp3.OkHttpClient okHttpClient;

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Web OkHttp] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean(ConnectionPool.class)
    public ConnectionPool ConnectionPool(FeignHttpClientProperties feignHttpClientProperties, OkHttpClientConnectionPoolFactory connectionPoolFactory) {
        int maxTotalConnections = feignHttpClientProperties.getMaxConnections();
        long timeToLive = feignHttpClientProperties.getTimeToLive();
        TimeUnit ttlUnit = feignHttpClientProperties.getTimeToLiveUnit();
        return connectionPoolFactory.create(maxTotalConnections, timeToLive, ttlUnit);
    }

    @Bean
    public okhttp3.OkHttpClient okHttpClient(OkHttpClientFactory okHttpClientFactory, ConnectionPool connectionPool, FeignClientProperties feignClientProperties, FeignHttpClientProperties feignHttpClientProperties) {
        FeignClientProperties.FeignClientConfiguration defaultConfig = feignClientProperties.getConfig().get("default");
        int connectTimeout = feignHttpClientProperties.getConnectionTimeout();
        int readTimeout = defaultConfig.getReadTimeout();
        boolean disableSslValidation = feignHttpClientProperties.isDisableSslValidation();
        boolean followRedirects = feignHttpClientProperties.isFollowRedirects();
        this.okHttpClient = okHttpClientFactory.createBuilder(disableSslValidation)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .followRedirects(followRedirects)
                .connectionPool(connectionPool)
                .addInterceptor(new OkHttpResponseInterceptor())
                .build();
        return this.okHttpClient;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(okhttp3.OkHttpClient okHttpClient) {
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory(okHttpClient);
        log.trace("[Kemido] |- Bean [Client Http Request Factory for OkHttp] Auto Configure.");
        return factory;
    }

    @PreDestroy
    public void destroy() {
        if (this.okHttpClient != null) {
            this.okHttpClient.dispatcher().executorService().shutdown();
            this.okHttpClient.connectionPool().evictAll();
        }
    }
}
