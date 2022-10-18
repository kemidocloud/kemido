package com.kemido.oauth2.data.jpa.service;

import com.kemido.data.core.repository.BaseRepository;
import com.kemido.data.core.service.BaseLayeredService;
import com.kemido.oauth2.data.jpa.repository.KemidoAuthorizationConsentRepository;
import com.kemido.oauth2.data.jpa.entity.KemidoAuthorizationConsent;
import com.kemido.oauth2.data.jpa.generator.KemidoAuthorizationConsentId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Description: KemidoAuthorizationConsentService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 */
@Service
public class KemidoAuthorizationConsentService extends BaseLayeredService<KemidoAuthorizationConsent, KemidoAuthorizationConsentId> {

    private static final Logger log = LoggerFactory.getLogger(KemidoAuthorizationConsentService.class);

    private final KemidoAuthorizationConsentRepository authorizationConsentRepository;

    @Autowired
    public KemidoAuthorizationConsentService(KemidoAuthorizationConsentRepository authorizationConsentRepository) {
        this.authorizationConsentRepository = authorizationConsentRepository;
    }

    @Override
    public BaseRepository<KemidoAuthorizationConsent, KemidoAuthorizationConsentId> getRepository() {
        return this.authorizationConsentRepository;
    }

    public Optional<KemidoAuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName) {
        Optional<KemidoAuthorizationConsent> result = this.authorizationConsentRepository.findByRegisteredClientIdAndPrincipalName(registeredClientId, principalName);
        log.debug("[Kemido] |- KemidoAuthorizationConsent Service findByRegisteredClientIdAndPrincipalName.");
        return result;
    }

    public void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName){
        this.authorizationConsentRepository.deleteByRegisteredClientIdAndPrincipalName(registeredClientId, principalName);
        log.debug("[Kemido] |- KemidoAuthorizationConsent Service deleteByRegisteredClientIdAndPrincipalName.");
    }
}
