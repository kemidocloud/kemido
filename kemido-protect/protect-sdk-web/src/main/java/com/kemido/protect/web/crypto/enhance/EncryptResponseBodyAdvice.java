package com.kemido.protect.web.crypto.enhance;

import com.kemido.assistant.core.constants.HttpHeaders;
import com.kemido.assistant.core.json.jackson2.utils.JacksonUtils;
import com.kemido.protect.core.annotation.Crypto;
import com.kemido.protect.core.exception.SessionInvalidException;
import com.kemido.protect.web.crypto.processor.HttpCryptoProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>Description: 响应体加密Advice </p>
 */
@RestControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(EncryptResponseBodyAdvice.class);

    private HttpCryptoProcessor httpCryptoProcessor;

    public void setInterfaceCryptoProcessor(HttpCryptoProcessor httpCryptoProcessor) {
        this.httpCryptoProcessor = httpCryptoProcessor;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {

        String methodName = methodParameter.getMethod().getName();
        Crypto crypto = methodParameter.getMethodAnnotation(Crypto.class);

        boolean isSupports = ObjectUtils.isNotEmpty(crypto) && crypto.responseEncrypt();

        log.trace("[Kemido] |- Is EncryptResponseBodyAdvice supports method [{}] ? Status is [{}].", methodName, isSupports);
        return isSupports;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        String sessionKey = request.getHeaders().get(HttpHeaders.X_HERODOTUS_SESSION).get(0);

        if (StringUtils.isBlank(sessionKey)) {
            log.warn("[Kemido] |- Cannot find Kemido Cloud custom session header. Use interface crypto founction need add X_HERODOTUS_SESSION to request header.");
            return body;
        }

        log.info("[Kemido] |- EncryptResponseBodyAdvice begin encrypt data.");

        String methodName = methodParameter.getMethod().getName();
        String className = methodParameter.getDeclaringClass().getName();

        try {
            String bodyString = JacksonUtils.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(body);
            String result = httpCryptoProcessor.encrypt(sessionKey, bodyString);
            if (StringUtils.isNotBlank(result)) {
                log.debug("[Kemido] |- Encrypt response body for rest method [{}] in [{}] finished.", methodName, className);
                return result;
            } else {
                return body;
            }
        } catch (JsonProcessingException e) {
            log.debug("[Kemido] |- Encrypt response body for rest method [{}] in [{}] catch error, skip encrypt operation.", methodName, className, e);
            return body;
        } catch (SessionInvalidException e) {
            log.error("[Kemido] |- Session is expired for encrypt response body for rest method [{}] in [{}], skip encrypt operation.", methodName, className, e);
            return body;
        }
    }
}
