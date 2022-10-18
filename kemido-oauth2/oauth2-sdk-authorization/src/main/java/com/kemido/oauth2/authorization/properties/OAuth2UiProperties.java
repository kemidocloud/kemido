package com.kemido.oauth2.authorization.properties;

import com.kemido.oauth2.core.constants.OAuth2Constants;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;

/**
 * <p>Description: OAuth2 界面配置属性 </p>
 */
@ConfigurationProperties(prefix = OAuth2Constants.PROPERTY_OAUTH2_UI)
public class OAuth2UiProperties {

    /**
     * UI 界面用户名标输入框 name 属性值
     */
    private String usernameParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
    /**
     * UI 界面密码标输入框 name 属性值
     */
    private String passwordParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
    /**
     * UI 界面Remember Me name 属性值
     */
    private String rememberMeParameter = AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY;
    /**
     * UI 界面验证码 name 属性值
     */
    private String captchaParameter = "captcha";
    /**
     * 登录页面地址
     */
    private String loginPageUrl = "/login";
    /**
     * 登录逻辑处理地址
     */
    private String loginProcessingUrl = loginPageUrl;
    /**
     * 失败处理地址
     */
    private String failureUrl = loginPageUrl;
    /**
     * 登录失败重定向地址
     */
    private String failureForwardUrl;
    /**
     * 登录成功重定向地址
     */
    private String successForwardUrl;
    /**
     * 关闭验证码显示，默认 false，显示
     */
    private Boolean closeCaptcha = false;
    /**
     * 验证码类别，默认为 Hutool Gif 类型
     */
    private String category = "HUTOOL_GIF";

    public String getUsernameParameter() {
        return usernameParameter;
    }

    public void setUsernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
        return passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }

    public String getRememberMeParameter() {
        return rememberMeParameter;
    }

    public void setRememberMeParameter(String rememberMeParameter) {
        this.rememberMeParameter = rememberMeParameter;
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public void setLoginPageUrl(String loginPageUrl) {
        this.loginPageUrl = loginPageUrl;
    }

    public String getFailureForwardUrl() {
        if (StringUtils.isNotBlank(failureForwardUrl)) {
            return failureForwardUrl;
        } else {
            return this.getLoginPageUrl() + "?error";
        }
    }

    public void setFailureForwardUrl(String failureForwardUrl) {
        this.failureForwardUrl = failureForwardUrl;
    }

    public String getSuccessForwardUrl() {
        return successForwardUrl;
    }

    public void setSuccessForwardUrl(String successForwardUrl) {
        this.successForwardUrl = successForwardUrl;
    }

    public Boolean getCloseCaptcha() {
        return closeCaptcha;
    }

    public void setCloseCaptcha(Boolean closeCaptcha) {
        this.closeCaptcha = closeCaptcha;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCaptchaParameter() {
        return captchaParameter;
    }

    public void setCaptchaParameter(String captchaParameter) {
        this.captchaParameter = captchaParameter;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("usernameParameter", usernameParameter)
                .add("passwordParameter", passwordParameter)
                .add("rememberMeParameter", rememberMeParameter)
                .add("captchaParameter", captchaParameter)
                .add("loginPageUrl", loginPageUrl)
                .add("loginProcessingUrl", loginProcessingUrl)
                .add("failureUrl", failureUrl)
                .add("failureForwardUrl", failureForwardUrl)
                .add("successForwardUrl", successForwardUrl)
                .add("closeCaptcha", closeCaptcha)
                .add("category", category)
                .toString();
    }
}
