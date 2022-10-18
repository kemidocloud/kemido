package com.kemido.oauth2.data.jpa.storage;

import com.kemido.oauth2.data.jpa.entity.KemidoAuthorizationConsent;
import com.kemido.oauth2.data.jpa.service.KemidoAuthorizationConsentService;
import com.kemido.oauth2.core.definition.domain.KemidoGrantedAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: 基于 JPA 的 OAuth2 认证服务 </p>
 */
public class JpaOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {

    private static final Logger log = LoggerFactory.getLogger(JpaOAuth2AuthorizationConsentService.class);

    private final KemidoAuthorizationConsentService kemidoAuthorizationConsentService;
    private final RegisteredClientRepository registeredClientRepository;

    public JpaOAuth2AuthorizationConsentService(KemidoAuthorizationConsentService kemidoAuthorizationConsentService, RegisteredClientRepository registeredClientRepository) {
        this.kemidoAuthorizationConsentService = kemidoAuthorizationConsentService;
        this.registeredClientRepository = registeredClientRepository;
    }

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        log.debug("[Kemido] |- Jpa OAuth2 Authorization Consent Service save entity.");
        this.kemidoAuthorizationConsentService.save(toEntity(authorizationConsent));
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        log.debug("[Kemido] |- Jpa OAuth2 Authorization Consent Service remove entity.");
        this.kemidoAuthorizationConsentService.deleteByRegisteredClientIdAndPrincipalName(
                authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        log.debug("[Kemido] |- Jpa OAuth2 Authorization Consent Service findById.");
        return this.kemidoAuthorizationConsentService.findByRegisteredClientIdAndPrincipalName(
                registeredClientId, principalName).map(this::toObject).orElse(null);
    }

    private OAuth2AuthorizationConsent toObject(KemidoAuthorizationConsent authorizationConsent) {
        String registeredClientId = authorizationConsent.getRegisteredClientId();
        RegisteredClient registeredClient = this.registeredClientRepository.findById(registeredClientId);
        if (registeredClient == null) {
            throw new DataRetrievalFailureException(
                    "The RegisteredClient with id '" + registeredClientId + "' was not found in the RegisteredClientRepository.");
        }

        OAuth2AuthorizationConsent.Builder builder = OAuth2AuthorizationConsent.withId(
                registeredClientId, authorizationConsent.getPrincipalName());
        if (authorizationConsent.getAuthorities() != null) {
            for (String authority : StringUtils.commaDelimitedListToSet(authorizationConsent.getAuthorities())) {
                builder.authority(new KemidoGrantedAuthority(authority));
            }
        }

        return builder.build();
    }

    private KemidoAuthorizationConsent toEntity(OAuth2AuthorizationConsent authorizationConsent) {
        KemidoAuthorizationConsent entity = new KemidoAuthorizationConsent();
        entity.setRegisteredClientId(authorizationConsent.getRegisteredClientId());
        entity.setPrincipalName(authorizationConsent.getPrincipalName());

        Set<String> authorities = new HashSet<>();
        for (GrantedAuthority authority : authorizationConsent.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        entity.setAuthorities(StringUtils.collectionToCommaDelimitedString(authorities));

        return entity;
    }
}
