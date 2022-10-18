package com.kemido.oauth2.core.response;

import com.kemido.assistant.core.domain.Result;
import com.kemido.oauth2.core.exception.SecurityGlobalExceptionHandler;
import com.kemido.web.core.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Description: 认证失败处理器 </p>
 */
public class KemidoAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Result<String> result = SecurityGlobalExceptionHandler.resolveSecurityException(exception, request.getRequestURI());
        response.setStatus(result.getStatus());
        WebUtils.renderJson(response, result);
    }
}
