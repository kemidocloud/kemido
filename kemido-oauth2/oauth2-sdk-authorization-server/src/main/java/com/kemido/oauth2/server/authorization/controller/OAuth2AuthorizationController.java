package com.kemido.oauth2.server.authorization.controller;

import com.kemido.data.core.service.WriteableService;
import com.kemido.oauth2.data.jpa.entity.KemidoAuthorization;
import com.kemido.oauth2.data.jpa.service.KemidoAuthorizationService;
import com.kemido.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2 认证管理接口 </p>
 */
@RestController
@RequestMapping("/authorize/authorization")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "OAuth2 认证管理接口")
})
public class OAuth2AuthorizationController extends BaseWriteableRestController<KemidoAuthorization, String> {

    private final KemidoAuthorizationService kemidoAuthorizationService;

    @Autowired
    public OAuth2AuthorizationController(KemidoAuthorizationService kemidoAuthorizationService) {
        this.kemidoAuthorizationService = kemidoAuthorizationService;
    }

    @Override
    public WriteableService<KemidoAuthorization, String> getWriteableService() {
        return this.kemidoAuthorizationService;
    }
}
