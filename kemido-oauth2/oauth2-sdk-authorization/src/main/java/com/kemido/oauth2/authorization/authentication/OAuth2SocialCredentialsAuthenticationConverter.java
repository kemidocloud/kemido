package com.kemido.oauth2.authorization.authentication;

import com.kemido.assistant.core.constants.BaseConstants;
import com.kemido.assistant.core.constants.HttpHeaders;
import com.kemido.assistant.core.enums.AccountType;
import com.kemido.oauth2.authorization.utils.OAuth2EndpointUtils;
import com.kemido.oauth2.core.definition.KemidoGrantType;
import com.kemido.protect.core.exception.SessionInvalidException;
import com.kemido.protect.web.crypto.processor.HttpCryptoProcessor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: 社交认证 Converter </p>
 */
public class OAuth2SocialCredentialsAuthenticationConverter implements AuthenticationConverter {

    private final HttpCryptoProcessor httpCryptoProcessor;

    public OAuth2SocialCredentialsAuthenticationConverter(HttpCryptoProcessor httpCryptoProcessor) {
        this.httpCryptoProcessor = httpCryptoProcessor;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        // grant_type (REQUIRED)
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!KemidoGrantType.SOCIAL.getValue().equals(grantType)) {
            return null;
        }

        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);

        // scope (OPTIONAL)
        String scope = OAuth2EndpointUtils.checkOptionalParameter(parameters, OAuth2ParameterNames.SCOPE);

        Set<String> requestedScopes = null;
        if (StringUtils.hasText(scope)) {
            requestedScopes = new HashSet<>(
                    Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        }

        // source (REQUIRED)
        String source = OAuth2EndpointUtils.checkRequiredParameter(parameters, BaseConstants.SOURCE);
        // others (REQUIRED)
        // TODO：2022-03-31 这里主要是作为参数的检查，社交登录内容比较多，后续根据实际情况添加
        if (StringUtils.hasText(source)) {
            AccountType accountType = AccountType.getAccountType(source);
            if (ObjectUtils.isNotEmpty(accountType)) {
                switch (accountType.getHandler()) {
                    case AccountType.PHONE_NUMBER_HANDLER:
                        OAuth2EndpointUtils.checkRequiredParameter(parameters, "mobile");
                        OAuth2EndpointUtils.checkRequiredParameter(parameters, "code");
                        break;
                    case AccountType.WECHAT_MINI_APP_HANDLER:
                        break;
                    default:
                        break;
                }
            }
        }

        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
        if (clientPrincipal == null) {
            OAuth2EndpointUtils.throwError(
                    OAuth2ErrorCodes.INVALID_REQUEST,
                    OAuth2ErrorCodes.INVALID_CLIENT,
                    OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        String sessionId = request.getHeader(HttpHeaders.X_HERODOTUS_SESSION);

        Map<String, Object> additionalParameters = parameters
                .entrySet()
                .stream()
                .filter(e -> !e.getKey().equals(OAuth2ParameterNames.GRANT_TYPE) &&
                        !e.getKey().equals(OAuth2ParameterNames.SCOPE))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> parameterDecrypt(e.getValue().get(0), sessionId)));

        return new OAuth2SocialCredentialsAuthenticationToken(clientPrincipal, requestedScopes, additionalParameters);
    }

    private Object parameterDecrypt(Object object, String sessionId) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(sessionId)) {
            if (ObjectUtils.isNotEmpty(object) && object instanceof String) {
                try {
                    return httpCryptoProcessor.decrypt(sessionId, object.toString());
                } catch (SessionInvalidException e) {
                    OAuth2EndpointUtils.throwError(
                            com.kemido.oauth2.core.constants.OAuth2ErrorCodes.SESSION_EXPIRED,
                            e.getMessage(),
                            OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
                }
            }
        }
        return object;
    }
}
