package com.kemido.web.rest.processor;

import com.kemido.web.core.context.KemidoApplicationContext;
import com.kemido.web.core.definition.OpenApiServerResolver;
import com.google.common.collect.ImmutableList;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

/**
 * <p>Description: 默认的OpenApi Serv处理器。 </p>
 */
public class DefaultOpenApiServerResolver implements OpenApiServerResolver {

    private final KemidoApplicationContext kemidoApplicationContext;

    public DefaultOpenApiServerResolver(KemidoApplicationContext kemidoApplicationContext) {
        this.kemidoApplicationContext = kemidoApplicationContext;
    }

    @Override
    public List<Server> getServers() {
        Server server = new Server();
        server.setUrl(kemidoApplicationContext.getServiceContext().getUrl());
        return ImmutableList.of(server);
    }
}
