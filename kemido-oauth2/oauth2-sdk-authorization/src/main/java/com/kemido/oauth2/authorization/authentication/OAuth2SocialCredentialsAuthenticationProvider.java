package com.kemido.oauth2.authorization.authentication;

import com.kemido.assistant.core.constants.BaseConstants;
import com.kemido.assistant.core.domain.AccessPrincipal;
import com.kemido.oauth2.authorization.utils.OAuth2AuthenticationProviderUtils;
import com.kemido.oauth2.core.definition.KemidoGrantType;
import com.kemido.oauth2.core.definition.service.EnhanceUserDetailsService;
import com.kemido.oauth2.core.exception.SocialCredentialsParameterBindingFailedException;
import com.kemido.oauth2.core.properties.OAuth2ComplianceProperties;
import cn.hutool.core.bean.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebRequestDataBinder;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: 社会化认证 Provider </p>
 */
public class OAuth2SocialCredentialsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(OAuth2SocialCredentialsAuthenticationProvider.class);

    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;

    public OAuth2SocialCredentialsAuthenticationProvider(OAuth2AuthorizationService authorizationService, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, UserDetailsService userDetailsService, OAuth2ComplianceProperties complianceProperties) {
        super(authorizationService, userDetailsService, complianceProperties);
        Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
        this.authorizationService = authorizationService;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, Map<String, Object> additionalParameters) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(Map<String, Object> additionalParameters) throws AuthenticationException {
        String source = (String) additionalParameters.get(BaseConstants.SOURCE);
        AccessPrincipal accessPrincipal = parameterBinder(additionalParameters);

        try {
            EnhanceUserDetailsService enhanceUserDetailsService = getUserDetailsService();
            UserDetails userDetails = enhanceUserDetailsService.loadUserBySocial(source, accessPrincipal);
            if (userDetails == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return userDetails;
        }
        catch (UsernameNotFoundException ex) {
            log.error("[Kemido] |- User name can not found for：[{}]", source);
            throw ex;
        }
        catch (InternalAuthenticationServiceException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        OAuth2SocialCredentialsAuthenticationToken socialCredentialsAuthentication =
                (OAuth2SocialCredentialsAuthenticationToken) authentication;

        OAuth2ClientAuthenticationToken clientPrincipal =
                OAuth2AuthenticationProviderUtils.getAuthenticatedClientElseThrowInvalidClient(socialCredentialsAuthentication);
        RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();

        if (!registeredClient.getAuthorizationGrantTypes().contains(KemidoGrantType.SOCIAL)) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }

        Authentication usernamePasswordAuthentication = getUsernamePasswordAuthentication(socialCredentialsAuthentication.getAdditionalParameters(), registeredClient.getId());

        // Default to configured scopes
        Set<String> authorizedScopes = validateScopes(socialCredentialsAuthentication.getScopes(), registeredClient);

        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .principalName(usernamePasswordAuthentication.getName())
                .authorizationGrantType(KemidoGrantType.SOCIAL)
                .authorizedScopes(authorizedScopes);

        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(usernamePasswordAuthentication)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .authorizedScopes(authorizedScopes)
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .authorizationGrantType(KemidoGrantType.SOCIAL)
                .authorizationGrant(socialCredentialsAuthentication);

        // ----- Access token -----
        OAuth2AccessToken accessToken = createOAuth2AccessToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, ERROR_URI);

        // ----- Refresh token -----
        OAuth2RefreshToken refreshToken = creatOAuth2RefreshToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, ERROR_URI, clientPrincipal, registeredClient);

        // ----- ID token -----
        OidcIdToken idToken = createOidcIdToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, ERROR_URI, socialCredentialsAuthentication.getScopes());

        OAuth2Authorization authorization = authorizationBuilder.build();

        this.authorizationService.save(authorization);

        log.debug("[Kemido] |- Social Credential returning OAuth2AccessTokenAuthenticationToken.");

        Map<String, Object> additionalParameters = idTokenAdditionalParameters(idToken);

        OAuth2AccessTokenAuthenticationToken accessTokenAuthenticationToken = new OAuth2AccessTokenAuthenticationToken(
                registeredClient, clientPrincipal, accessToken, refreshToken, additionalParameters);
        return createOAuth2AccessTokenAuthenticationToken(usernamePasswordAuthentication, accessTokenAuthenticationToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        boolean supports = OAuth2SocialCredentialsAuthenticationToken.class.isAssignableFrom(authentication);
        log.trace("[Kemido] |- Resource Owner Password Authentication is supports! [{}]", supports);
        return supports;
    }

    private AccessPrincipal parameterBinder(Map<String, Object> parameters) throws SocialCredentialsParameterBindingFailedException {
        AccessPrincipal accessPrincipal = new AccessPrincipal();

        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues(parameters);

        WebRequestDataBinder webRequestDataBinder  = new WebRequestDataBinder(accessPrincipal);
        webRequestDataBinder.bind(mutablePropertyValues);
        if (BeanUtil.isNotEmpty(accessPrincipal)) {
            return accessPrincipal;
        }

        throw new SocialCredentialsParameterBindingFailedException("Internet authentication parameter bindng is not correct!");
    }


}
