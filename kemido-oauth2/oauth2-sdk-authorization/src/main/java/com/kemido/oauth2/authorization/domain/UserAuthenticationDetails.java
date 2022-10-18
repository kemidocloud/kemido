package com.kemido.oauth2.authorization.domain;

import java.util.Set;

/**
 * <p>Description: 用户登录额外信息 </p>
 */
public class UserAuthenticationDetails {

    private String userId;

    private String userName;

    private Set<String> roles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
