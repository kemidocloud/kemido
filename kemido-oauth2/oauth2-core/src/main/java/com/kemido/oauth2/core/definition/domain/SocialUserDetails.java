package com.kemido.oauth2.core.definition.domain;

/**
 * <p>Description: 社交登录用户信息详情 </p>
 */
public interface SocialUserDetails {

    /**
     * 获取社交登录唯一标识
     * @return String
     */
    String getUuid();

    /**
     * 获取社交登录分类标识
     * @return String
     */
    String getSource();

    String getPhoneNumber();

    String getAvatar();

    String getUserName();

    String getNickName();
}
