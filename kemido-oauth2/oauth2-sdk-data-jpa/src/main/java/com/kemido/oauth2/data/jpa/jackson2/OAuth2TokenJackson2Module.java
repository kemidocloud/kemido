package com.kemido.oauth2.data.jpa.jackson2;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

/**
 * <p>Description: 自定义 OAutho2 Module </p>
 */
public class OAuth2TokenJackson2Module extends SimpleModule {

    public OAuth2TokenJackson2Module() {
        super(OAuth2TokenJackson2Module.class.getName(), new Version(1, 0, 0, null, null, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        SecurityJackson2Modules.enableDefaultTyping(context.getOwner());
        context.setMixInAnnotations(ClientAuthenticationMethod.class, ClientAuthenticationMethodMixin.class);
        context.setMixInAnnotations(AuthorizationGrantType.class, AuthorizationGrantTypeMixin.class);
        context.setMixInAnnotations(TokenSettings.class, TokenSettingsMixin.class);
        context.setMixInAnnotations(ClientSettings.class, ClientSettingsMixin.class);
        context.setMixInAnnotations(RegisteredClient.class, RegisteredClientMixin.class);
        context.setMixInAnnotations(OAuth2ClientAuthenticationToken.class, OAuth2ClientAuthenticationTokenMixin.class);
    }
}
