package com.kemido.assistant.core.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>Description: 秘钥缓存存储实体 </p>
 */
public class SecretKey implements Serializable {

    /**
     * 数据存储身份标识
     */
    private String identity;
    /**
     * 对称加密算法秘钥
     */
    private String symmetricKey;

    /**
     * 服务器端非对称加密算法公钥
     * 1. RSA 为 Base64 格式
     * 2. SM2 为 Hex 格式
     */
    private String publicKey;

    /**
     * 服务器端非对称加密算法私钥
     */
    private String privateKey;

    /**
     * 本系统授权码模式中后台返回的 State
     */
    private String state;

    /**
     * 创建时间戳
     */
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public SecretKey() {
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSymmetricKey() {
        return symmetricKey;
    }

    public void setSymmetricKey(String symmetricKey) {
        this.symmetricKey = symmetricKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecretKey secretKey = (SecretKey) o;
        return Objects.equal(identity, secretKey.identity) && Objects.equal(timestamp, secretKey.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identity, timestamp);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identity", identity)
                .add("symmetricKey", symmetricKey)
                .add("publicKey", publicKey)
                .add("privateKey", privateKey)
                .add("state", state)
                .add("timestamp", timestamp)
                .toString();
    }
}
