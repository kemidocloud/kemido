package com.kemido.oauth2.core.jackson2;

import com.kemido.oauth2.core.definition.domain.FormLoginWebAuthenticationDetails;
import com.kemido.oauth2.core.definition.domain.KemidoGrantedAuthority;
import com.kemido.oauth2.core.definition.domain.KemidoUser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.security.jackson2.SecurityJackson2Modules;

/**
 * <p>Description: 自定义 User Details Module </p>
 */
public class KemidoJackson2Module extends SimpleModule {

    public KemidoJackson2Module() {
        super(KemidoJackson2Module.class.getName(), new Version(1, 0, 0, null, null, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        SecurityJackson2Modules.enableDefaultTyping(context.getOwner());
        context.setMixInAnnotations(KemidoUser.class, KemidoUserMixin.class);
        context.setMixInAnnotations(KemidoGrantedAuthority.class, KemidoGrantedAuthorityMixin.class);
        context.setMixInAnnotations(FormLoginWebAuthenticationDetails.class, FormLoginWebAuthenticationDetailsMixin.class);
    }
}
