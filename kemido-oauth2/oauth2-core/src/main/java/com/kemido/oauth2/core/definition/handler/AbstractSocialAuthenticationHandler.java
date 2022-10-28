package com.kemido.oauth2.core.definition.handler;

import com.kemido.access.core.exception.AccessIdentityVerificationFailedException;
import com.kemido.assistant.core.domain.AccessPrincipal;
import com.kemido.oauth2.core.exception.SocialCredentialsUserBindingFailedException;
import com.kemido.oauth2.core.definition.domain.KemidoUser;
import com.kemido.oauth2.core.definition.domain.SocialUserDetails;
import com.kemido.oauth2.core.definition.handler.SocialAuthenticationHandler;
import com.kemido.oauth2.core.exception.UsernameAlreadyExistsException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: 抽象的社交登录处理器 </p>
 * <p>
 * 实现社交登录和手机号码登录的主要流程逻辑
 */
public abstract class AbstractSocialAuthenticationHandler implements SocialAuthenticationHandler {

    /**
     * 第三方登录以及手机号码验证的认证，认证成功返回对应系统中的返回信息
     *
     * @param source          社交登录提供者分类
     * @param accessPrincipal 社交登录所需要的参数信息
     * @return 认证成功后返回的信息 {@link SocialUserDetails}
     * @throws AccessIdentityVerificationFailedException 社交登录认证出错
     */
    public abstract SocialUserDetails identity(String source, AccessPrincipal accessPrincipal) throws AccessIdentityVerificationFailedException;

    /**
     * 根据社交登录返回的用户信息，查询系统中是否有响应的信息
     *
     * @param socialUserDetails 第三方系统或者手机号码认证成功后返回的信息 {@link SocialUserDetails}
     * @return 系统UserDetails {@link SocialUserDetails}
     */
    public abstract SocialUserDetails isUserExist(SocialUserDetails socialUserDetails);

    /**
     * 系统用户注册
     * <p>
     * 根据社交用户提供的一些信息，进行系统用户的注册
     *
     * @param socialUserDetails {@link SocialUserDetails}
     * @return 系统用户 {@link KemidoUser}
     * @throws UsernameAlreadyExistsException 用户名已经存在
     */
    public abstract KemidoUser register(SocialUserDetails socialUserDetails) throws UsernameAlreadyExistsException;

    /**
     * 系统用户与社交用户绑定操作
     *
     * @param socialUserDetails 第三方系统或者手机号码认证成功后返回的信息 {@link SocialUserDetails}
     * @param userId            系统用户的ID。
     * @throws SocialCredentialsUserBindingFailedException 绑定出现错误Exception
     */
    public abstract void binding(String userId, SocialUserDetails socialUserDetails) throws SocialCredentialsUserBindingFailedException;

    /**
     * 随着系统业务复杂度的增加，系统用户注册成功之后，也许还会进行其它额外的操作，来补充新用户的相关信息。
     * 所以提供一个方法，方便进行新用户其它业务信息的操作。建议采用是异步操作。
     *
     * @param KemidoUser 系统用户信息 {@link KemidoUser}
     * @param socialUserDetails    社交登录过程中，第三方系统返回的新信息
     */
    public abstract void additionalRegisterOperation(KemidoUser KemidoUser, SocialUserDetails socialUserDetails);

    /**
     * 系统用户注册
     * <p>
     * 根据社交用户提供的一些信息，进行系统用户的注册
     *
     * @param socialUserDetails {@link SocialUserDetails}
     * @return 系统用户 {@link KemidoUser}
     */
    public abstract KemidoUser signIn(SocialUserDetails socialUserDetails);

    /**
     * 社交用户登录后，附加的其它操作
     *
     * @param KemidoUser 系统用户信息 {@link KemidoUser}
     * @param newSocialUserDetails 社交登录过程中，第三方系统返回的新信息 {@link SocialUserDetails}
     * @param oldSocialUserDetails 系统中已经存在的社交用户信息
     */
    public abstract void additionalSignInOperation(KemidoUser KemidoUser, SocialUserDetails newSocialUserDetails, SocialUserDetails oldSocialUserDetails);

    /**
     * 社交登录
     * <p>
     * 1. 首先在第三方系统进行认证，或者手机号码、扫码认证。返回认证后的信息
     * 2. 根据认证返回的信息，在系统中查询是否有对应的用户信息。
     * 2.1. 如果有对应的信息，根据需要更新社交用户的信息，然后返回系统用户信息，进行登录。
     * 2.2. 如果没有对应信息，就先进行用户的注册，然后进行社交用户和系统用户的绑定。
     *
     * @param source          社交登录提供者分类
     * @param accessPrincipal 社交登录所需要的信息
     */
    @Override
    public KemidoUser authentication(String source, AccessPrincipal accessPrincipal) throws AuthenticationException {
        SocialUserDetails newSocialUserDetails = this.identity(source, accessPrincipal);
        SocialUserDetails oldSocialUserDetails = this.isUserExist(newSocialUserDetails);

        if (ObjectUtils.isEmpty(oldSocialUserDetails)) {
            KemidoUser KemidoUser = this.register(newSocialUserDetails);
            this.binding(KemidoUser.getUserId(), newSocialUserDetails);
            this.additionalRegisterOperation(KemidoUser, newSocialUserDetails);
            return KemidoUser;
        } else {
            KemidoUser KemidoUser = this.signIn(oldSocialUserDetails);
            this.additionalSignInOperation(KemidoUser, newSocialUserDetails, oldSocialUserDetails);
            return KemidoUser;
        }
    }
}
