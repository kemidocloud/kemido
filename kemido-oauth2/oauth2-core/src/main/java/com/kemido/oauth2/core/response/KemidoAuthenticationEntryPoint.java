package com.kemido.oauth2.core.response;

import com.kemido.assistant.core.domain.Result;
import com.kemido.oauth2.core.exception.SecurityGlobalExceptionHandler;
import com.kemido.web.core.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Description: 自定义未认证处理 </p>
 */
public class KemidoAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<String> result = SecurityGlobalExceptionHandler.resolveSecurityException(authException, request.getRequestURI());
        response.setStatus(result.getStatus());
        WebUtils.renderJson(response, result);
    }
}
