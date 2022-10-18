package com.kemido.oauth2.data.jpa.service;

import com.kemido.data.core.repository.BaseRepository;
import com.kemido.data.core.service.BaseLayeredService;
import com.kemido.oauth2.data.jpa.entity.KemidoAuthorization;
import com.kemido.oauth2.data.jpa.repository.KemidoAuthorizationRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>Description: KemidoAuthorizationService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 */
@Service
public class KemidoAuthorizationService extends BaseLayeredService<KemidoAuthorization, String> {

    private static final Logger log = LoggerFactory.getLogger(KemidoAuthorizationService.class);

    private final KemidoAuthorizationRepository kemidoAuthorizationRepository;

    @Autowired
    public KemidoAuthorizationService(KemidoAuthorizationRepository kemidoAuthorizationRepository) {
        this.kemidoAuthorizationRepository = kemidoAuthorizationRepository;
    }

    @Override
    public BaseRepository<KemidoAuthorization, String> getRepository() {
        return this.kemidoAuthorizationRepository;
    }

    public Optional<KemidoAuthorization> findByState(String state) {
        Optional<KemidoAuthorization> result = this.kemidoAuthorizationRepository.findByState(state);
        log.debug("[Kemido] |- KemidoAuthorization Service findByState.");
        return result;
    }

    public Optional<KemidoAuthorization> findByAuthorizationCode(String authorizationCode) {
        Optional<KemidoAuthorization> result = this.kemidoAuthorizationRepository.findByAuthorizationCode(authorizationCode);
        log.debug("[Kemido] |- KemidoAuthorization Service findByAuthorizationCode.");
        return result;
    }

    public Optional<KemidoAuthorization> findByAccessToken(String accessToken) {
        Optional<KemidoAuthorization> result = this.kemidoAuthorizationRepository.findByAccessToken(accessToken);
        log.debug("[Kemido] |- KemidoAuthorization Service findByAccessToken.");
        return result;
    }

    public Optional<KemidoAuthorization> findByRefreshToken(String refreshToken) {
        Optional<KemidoAuthorization> result = this.kemidoAuthorizationRepository.findByRefreshToken(refreshToken);
        log.debug("[Kemido] |- KemidoAuthorization Service findByRefreshToken.");
        return result;
    }

    public Optional<KemidoAuthorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValue(String token) {

        Specification<KemidoAuthorization> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("state"), token));
            predicates.add(criteriaBuilder.equal(root.get("authorizationCode"), token));
            predicates.add(criteriaBuilder.equal(root.get("accessToken"), token));
            predicates.add(criteriaBuilder.equal(root.get("refreshToken"), token));

            Predicate[] predicateArray = new Predicate[predicates.size()];
            criteriaQuery.where(criteriaBuilder.or(predicates.toArray(predicateArray)));
            return criteriaQuery.getRestriction();
        };

        Optional<KemidoAuthorization> result = this.kemidoAuthorizationRepository.findOne(specification);
        log.debug("[Kemido] |- KemidoAuthorization Service findByDetection.");
        return result;
    }

    public void clearHistoryToken() {
        this.kemidoAuthorizationRepository.deleteByRefreshTokenExpiresAtBefore(LocalDateTime.now());
        log.debug("[Kemido] |- KemidoAuthorization Service clearExpireAccessToken.");
    }

    public List<KemidoAuthorization> findAvailableAuthorizations(String registeredClientId, String principalName) {
        List<KemidoAuthorization> authorizations = this.kemidoAuthorizationRepository.findAllByRegisteredClientIdAndPrincipalNameAndAccessTokenExpiresAtAfter(registeredClientId, principalName, LocalDateTime.now());
        log.debug("[Kemido] |- KemidoAuthorization Service findAvailableAuthorizations.");
        return authorizations;
    }

    public int findAuthorizationCount(String registeredClientId, String principalName) {
        List<KemidoAuthorization> authorizations = findAvailableAuthorizations(registeredClientId, principalName);
        int count = 0;
        if (CollectionUtils.isNotEmpty(authorizations)) {
            count = authorizations.size();
        }
        log.debug("[Kemido] |- KemidoAuthorization Service current authorization count is [{}].", count);
        return count;
    }
}
