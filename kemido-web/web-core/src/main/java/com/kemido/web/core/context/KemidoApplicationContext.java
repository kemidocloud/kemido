package com.kemido.web.core.context;

import com.kemido.assistant.core.support.PropertyFinder;
import com.kemido.assistant.core.utils.EnvUtils;
import com.kemido.web.core.properties.EndpointProperties;
import com.kemido.web.core.properties.PlatformProperties;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;

/**
 * <p>Description: 常用环境信息包装类 </p>
 */
public class KemidoApplicationContext {

    private final PlatformProperties platformProperties;
    private final EndpointProperties endpointProperties;
    private final ServerProperties serverProperties;
    private final ServiceContext serviceContext;
    private final ApplicationContext applicationContext;

    public KemidoApplicationContext(ApplicationContext applicationContext, PlatformProperties platformProperties, EndpointProperties endpointProperties, ServerProperties serverProperties) {
        this.platformProperties = platformProperties;
        this.endpointProperties = endpointProperties;
        this.serverProperties = serverProperties;
        this.applicationContext = applicationContext;
        this.serviceContext = ServiceContext.getInstance();
        initServiceContext();
    }

    public void initServiceContext()  {
        this.serviceContext.setArchitecture(this.platformProperties.getArchitecture());
        this.serviceContext.setDataAccessStrategy(this.platformProperties.getDataAccessStrategy());
        this.serviceContext.setGatewayAddress(this.endpointProperties.getGatewayServiceUri());
        this.serviceContext.setPort(String.valueOf(this.getPort()));
        this.serviceContext.setIp(getHostAddress());
        this.serviceContext.setApplicationContext(applicationContext);
        this.serviceContext.setApplicationName(PropertyFinder.getApplicationName(applicationContext.getEnvironment()));
    }

    private String getHostAddress() {
        String address = EnvUtils.getHostAddress();
        if (ObjectUtils.isNotEmpty(serverProperties.getAddress())) {
            address = serverProperties.getAddress().getHostAddress();
        }

        if (StringUtils.isNotBlank(address)) {
            return address;
        } else {
            return "localhost";
        }
    }

    private Integer getPort() {
        Integer port = serverProperties.getPort();
        if (ObjectUtils.isNotEmpty(port)) {
            return port;
        } else {
            return 8080;
        }
    }

    public PlatformProperties getPlatformProperties() {
        return platformProperties;
    }

    public ServiceContext getServiceContext() {
        return serviceContext;
    }
}
