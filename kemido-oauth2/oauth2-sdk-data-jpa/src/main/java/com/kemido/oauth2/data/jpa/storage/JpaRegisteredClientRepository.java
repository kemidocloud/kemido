package com.kemido.oauth2.data.jpa.storage;

import com.kemido.oauth2.core.jackson2.KemidoJackson2Module;
import com.kemido.oauth2.data.jpa.entity.KemidoRegisteredClient;
import com.kemido.oauth2.data.jpa.service.KemidoRegisteredClientService;
import com.kemido.oauth2.data.jpa.utils.OAuth2AuthorizationUtils;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import com.kemido.oauth2.data.jpa.jackson2.OAuth2TokenJackson2Module;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: 基于Jpa 的 RegisteredClient服务 </p>
 */
public class JpaRegisteredClientRepository implements RegisteredClientRepository {

    private static final Logger log = LoggerFactory.getLogger(JpaRegisteredClientRepository.class);

    private final KemidoRegisteredClientService kemidoRegisteredClientService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JpaRegisteredClientRepository(KemidoRegisteredClientService kemidoRegisteredClientService, PasswordEncoder passwordEncoder) {
        this.kemidoRegisteredClientService = kemidoRegisteredClientService;
        this.passwordEncoder = passwordEncoder;

        ClassLoader classLoader = JpaRegisteredClientRepository.class.getClassLoader();
        List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
        this.objectMapper.registerModules(securityModules);
        this.objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
        this.objectMapper.registerModules(new KemidoJackson2Module());
        this.objectMapper.registerModules(new OAuth2TokenJackson2Module());
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        log.debug("[Kemido] |- Jpa Registered Client Repository save entity.");
        this.kemidoRegisteredClientService.save(toEntity(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        log.debug("[Kemido] |- Jpa Registered Client Repository findById.");
        KemidoRegisteredClient kemidoRegisteredClient = this.kemidoRegisteredClientService.findById(id);
        if (ObjectUtils.isNotEmpty(kemidoRegisteredClient)) {
            return toObject(kemidoRegisteredClient);
        }
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        log.debug("[Kemido] |- Jpa Registered Client Repository findByClientId.");
        return this.kemidoRegisteredClientService.findByClientId(clientId).map(this::toObject).orElse(null);
    }

    public void remove(String id) {
        log.debug("[Kemido] |- Jpa Registered Client Repository remove.");
        this.kemidoRegisteredClientService.deleteById(id);
    }

    private RegisteredClient toObject(KemidoRegisteredClient kemidoRegisteredClient) {
        Set<String> clientAuthenticationMethods = StringUtils.commaDelimitedListToSet(
                kemidoRegisteredClient.getClientAuthenticationMethods());
        Set<String> authorizationGrantTypes = StringUtils.commaDelimitedListToSet(
                kemidoRegisteredClient.getAuthorizationGrantTypes());
        Set<String> redirectUris = StringUtils.commaDelimitedListToSet(
                kemidoRegisteredClient.getRedirectUris());
        Set<String> clientScopes = StringUtils.commaDelimitedListToSet(
                kemidoRegisteredClient.getScopes());

        RegisteredClient.Builder builder = RegisteredClient.withId(kemidoRegisteredClient.getId())
                .clientId(kemidoRegisteredClient.getClientId())
                .clientIdIssuedAt(DateUtil.toInstant(kemidoRegisteredClient.getClientIdIssuedAt()))
                .clientSecret(kemidoRegisteredClient.getClientSecret())
                .clientSecretExpiresAt(DateUtil.toInstant(kemidoRegisteredClient.getClientSecretExpiresAt()))
                .clientName(kemidoRegisteredClient.getClientName())
                .clientAuthenticationMethods(authenticationMethods ->
                        clientAuthenticationMethods.forEach(authenticationMethod ->
                                authenticationMethods.add(OAuth2AuthorizationUtils.resolveClientAuthenticationMethod(authenticationMethod))))
                .authorizationGrantTypes((grantTypes) ->
                        authorizationGrantTypes.forEach(grantType ->
                                grantTypes.add(OAuth2AuthorizationUtils.resolveAuthorizationGrantType(grantType))))
                .redirectUris((uris) -> uris.addAll(redirectUris))
                .scopes((scopes) -> scopes.addAll(clientScopes));

        Map<String, Object> clientSettingsMap = parseMap(kemidoRegisteredClient.getClientSettings());
        builder.clientSettings(ClientSettings.withSettings(clientSettingsMap).build());

        Map<String, Object> tokenSettingsMap = parseMap(kemidoRegisteredClient.getTokenSettings());
        builder.tokenSettings(TokenSettings.withSettings(tokenSettingsMap).build());

        return builder.build();
    }

    private KemidoRegisteredClient toEntity(RegisteredClient registeredClient) {
        List<String> clientAuthenticationMethods = new ArrayList<>(registeredClient.getClientAuthenticationMethods().size());
        registeredClient.getClientAuthenticationMethods().forEach(clientAuthenticationMethod ->
                clientAuthenticationMethods.add(clientAuthenticationMethod.getValue()));

        List<String> authorizationGrantTypes = new ArrayList<>(registeredClient.getAuthorizationGrantTypes().size());
        registeredClient.getAuthorizationGrantTypes().forEach(authorizationGrantType ->
                authorizationGrantTypes.add(authorizationGrantType.getValue()));

        KemidoRegisteredClient entity = new KemidoRegisteredClient();
        entity.setId(registeredClient.getId());
        entity.setClientId(registeredClient.getClientId());
        entity.setClientIdIssuedAt(DateUtil.toLocalDateTime(registeredClient.getClientIdIssuedAt()));
        entity.setClientSecret(encode(registeredClient.getClientSecret()));
        entity.setClientSecretExpiresAt(DateUtil.toLocalDateTime(registeredClient.getClientSecretExpiresAt()));
        entity.setClientName(registeredClient.getClientName());
        entity.setClientAuthenticationMethods(StringUtils.collectionToCommaDelimitedString(clientAuthenticationMethods));
        entity.setAuthorizationGrantTypes(StringUtils.collectionToCommaDelimitedString(authorizationGrantTypes));
        entity.setRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getRedirectUris()));
        entity.setScopes(StringUtils.collectionToCommaDelimitedString(registeredClient.getScopes()));
        entity.setClientSettings(writeMap(registeredClient.getClientSettings().getSettings()));
        entity.setTokenSettings(writeMap(registeredClient.getTokenSettings().getSettings()));

        return entity;
    }

    private String encode(String value) {
        if (value != null) {
            return this.passwordEncoder.encode(value);
        }
        return null;
    }

    private Map<String, Object> parseMap(String data) {
        try {
            return this.objectMapper.readValue(data, new TypeReference<Map<String, Object>>() {});
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    private String writeMap(Map<String, Object> data) {
        try {
            return this.objectMapper.writeValueAsString(data);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }
}
