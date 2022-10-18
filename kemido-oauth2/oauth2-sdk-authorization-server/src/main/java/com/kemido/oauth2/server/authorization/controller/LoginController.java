package com.kemido.oauth2.server.authorization.controller;

import com.kemido.oauth2.authorization.properties.OAuth2UiProperties;
import com.kemido.oauth2.core.utils.SymmetricUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

/**
 * <p>Description: Security 登录控制器 </p>
 *
 * @see org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer
 * @see org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter
 */
@Controller
public class LoginController {

    private static final String DEFAULT_LOGIN_PAGE_VIEW = "login";
    private static final String DEFAULT_ERROR_PAGE_VIEW = "error";

    private final OAuth2UiProperties uiProperties;

    @Autowired
    public LoginController(OAuth2UiProperties uiProperties) {
        this.uiProperties = uiProperties;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Map<String, Object> model, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView(DEFAULT_LOGIN_PAGE_VIEW);

        boolean loginError = isErrorPage(request);
        boolean logoutSuccess = isLogoutSuccess(request);
        String errorMessage = getErrorMessage(request);

        Map<String, String> hiddenInputs = hiddenInputs(request);

        // 登录可配置用户名参数
        modelAndView.addObject("vulgar_tycoon", uiProperties.getUsernameParameter());
        // 登录可配置密码参数
        modelAndView.addObject("beast", uiProperties.getPasswordParameter());
        modelAndView.addObject("anubis", uiProperties.getRememberMeParameter());
        modelAndView.addObject("graphic", uiProperties.getCaptchaParameter());
        modelAndView.addObject("hide_verification_code", uiProperties.getCloseCaptcha());
        // Security 隐藏域
        // AES加密key
        modelAndView.addObject("soup_spoon", SymmetricUtils.getEncryptedSymmetricKey());
        // 验证码类别
        modelAndView.addObject("verification_category", uiProperties.getCategory());
        modelAndView.addObject("hidden_inputs", hiddenInputs);
        modelAndView.addObject("login_error", loginError);
        modelAndView.addObject("logout_success", logoutSuccess);
        modelAndView.addObject("message", StringUtils.isNotBlank(errorMessage) ? HtmlUtils.htmlEscape(errorMessage) : null);

        return modelAndView;
    }

    private boolean isErrorPage(HttpServletRequest request) {
        String failureUrl = DEFAULT_LOGIN_PAGE_VIEW + "?" + DEFAULT_ERROR_PAGE_VIEW;
        return matches(request, failureUrl);
    }

    private boolean isLogoutSuccess(HttpServletRequest request) {
        String logoutSuccessUrl = DEFAULT_LOGIN_PAGE_VIEW + "?logout";
        return matches(request, logoutSuccessUrl);
    }

    private Map<String, String> hiddenInputs(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        return (token != null) ? Collections.singletonMap(token.getParameterName(), token.getToken())
                : Collections.emptyMap();
    }

    private String getErrorMessage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (ObjectUtils.isNotEmpty(session)) {
            String message = (String) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ObjectUtils.isNotEmpty(message)) {
                return message;
            }
        }

        return null;
    }

    private boolean matches(HttpServletRequest request, String url) {
        if (!HttpMethod.GET.name().equals(request.getMethod()) || url == null) {
            return false;
        }
        String uri = request.getRequestURI();
        int pathParamIndex = uri.indexOf(';');
        if (pathParamIndex > 0) {
            // strip everything after the first semi-colon
            uri = uri.substring(0, pathParamIndex);
        }
        if (request.getQueryString() != null) {
            uri += "?" + request.getQueryString();
        }
        if ("".equals(request.getContextPath())) {
            return uri.equals(url);
        }
        return uri.equals(request.getContextPath() + url);
    }


}
