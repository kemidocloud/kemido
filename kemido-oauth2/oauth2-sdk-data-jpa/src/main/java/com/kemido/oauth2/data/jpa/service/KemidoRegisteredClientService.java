package com.kemido.oauth2.data.jpa.service;

import com.kemido.data.core.repository.BaseRepository;
import com.kemido.data.core.service.BaseLayeredService;
import com.kemido.oauth2.data.jpa.repository.KemidoRegisteredClientRepository;
import com.kemido.oauth2.data.jpa.entity.KemidoRegisteredClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Description: KemidoRegisteredClientService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 */
@Service
public class KemidoRegisteredClientService extends BaseLayeredService<KemidoRegisteredClient, String> {

    private static final Logger log = LoggerFactory.getLogger(KemidoRegisteredClientService.class);

    private final KemidoRegisteredClientRepository registeredClientRepository;

    @Autowired
    public KemidoRegisteredClientService(KemidoRegisteredClientRepository registeredClientRepository) {
        this.registeredClientRepository = registeredClientRepository;
    }

    @Override
    public BaseRepository<KemidoRegisteredClient, String> getRepository() {
        return registeredClientRepository;
    }

    public Optional<KemidoRegisteredClient> findByClientId(String clientId) {
        Optional<KemidoRegisteredClient> result = this.registeredClientRepository.findByClientId(clientId);
        log.debug("[Kemido] |- KemidoRegisteredClient Service findByClientId.");
        return result;
    }
}
