package com.kemido.assistant.core.domain;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 外部程序接入必要参数 </p>
 */
public class AccessPrincipal {

    /* ---------- 共性参数 ---------- */

    @Schema(name = "后回调时带的参数code", title = "访问AuthorizeUrl后回调时带的参数code")
    private String code;

    /* ---------- 微信小程序常用参数 ---------- */

    @Schema(name = "小程序appId", title = "小程序appId")
    private String appId;

    @Schema(name = "消息密文", title = "微信小程序消息密文")
    private String encryptedData;

    @Schema(name = "加密算法的初始向量", title = "微信小程序加密算法的初始向量")
    private String iv;

    @Schema(name = "小程序用户openId", title = "小程序用户openId")
    private String openId;

    @Schema(name = "会话密钥", title = "微信小程序会话密钥")
    private String sessionKey;

    @Schema(name = "唯一ID", title = "微信唯一ID")
    private String unionId;

    @Schema(name = "用户非敏感信息", title = "微信小程序用户非敏感信息")
    private String rawData;

    @Schema(name = "签名", title = "微信小程序签名")
    private String signature;

    /* ---------- Just Auth 标准参数 ---------- */

    @Schema(name = "后回调时带的参数auth_code", title = "该参数目前只使用于支付宝登录")
    private String auth_code;

    @Schema(name = "后回调时带的参数state", title = "用于和请求AuthorizeUrl前的state比较，防止CSRF攻击")
    private String state;

    @Schema(name = "华为授权登录接受code的参数名")
    private String authorization_code;

    @Schema(name = "回调后返回的oauth_token", title = "Twitter回调后返回的oauth_token")
    private String oauth_token;

    @Schema(name = "回调后返回的oauth_verifier", title = "Twitter回调后返回的oauth_verifier")
    private String oauth_verifier;

    /* ---------- 手机短信验证码 ---------- */

    @Schema(name = "手机号码", title = "手机短信登录唯一标识")
    private String mobile;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAuthorization_code() {
        return authorization_code;
    }

    public void setAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
    }

    public String getOauth_token() {
        return oauth_token;
    }

    public void setOauth_token(String oauth_token) {
        this.oauth_token = oauth_token;
    }

    public String getOauth_verifier() {
        return oauth_verifier;
    }

    public void setOauth_verifier(String oauth_verifier) {
        this.oauth_verifier = oauth_verifier;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("appId", appId)
                .add("encryptedData", encryptedData)
                .add("iv", iv)
                .add("openId", openId)
                .add("sessionKey", sessionKey)
                .add("unionId", unionId)
                .add("rawData", rawData)
                .add("signature", signature)
                .add("auth_code", auth_code)
                .add("state", state)
                .add("authorization_code", authorization_code)
                .add("oauth_token", oauth_token)
                .add("oauth_verifier", oauth_verifier)
                .add("mobile", mobile)
                .toString();
    }
}
