package com.kemido.oauth2.core.response;

import com.kemido.assistant.core.domain.Result;
import com.kemido.oauth2.core.exception.SecurityGlobalExceptionHandler;
import com.kemido.web.core.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Description: 访问拒绝处理器 </p>
 */
public class KemidoAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<String> result = SecurityGlobalExceptionHandler.resolveException(accessDeniedException, request.getRequestURI());
        response.setStatus(result.getStatus());
        WebUtils.renderJson(response, result);
    }
}
