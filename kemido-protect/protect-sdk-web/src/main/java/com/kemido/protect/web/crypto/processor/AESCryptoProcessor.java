package com.kemido.protect.web.crypto.processor;

import com.kemido.protect.core.definition.SymmetricCryptoProcessor;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: AES 加密算法处理器 </p>
 */
public class AESCryptoProcessor implements SymmetricCryptoProcessor {

    private static final Logger log = LoggerFactory.getLogger(AESCryptoProcessor.class);

    @Override
    public String createKey() {
        return RandomUtil.randomStringUpper(16);
    }

    @Override
    public String decrypt(String data, String key) {
        AES aes = SecureUtil.aes(StrUtil.utf8Bytes(key));
        byte[] result = aes.decrypt(Base64.decode(StrUtil.utf8Bytes(data)));
        log.trace("[Kemido] |- AES crypto decrypt data, value is : [{}]", result);
        return StrUtil.utf8Str(result);
    }

    @Override
    public String encrypt(String data, String key) {
        AES aes = SecureUtil.aes(StrUtil.utf8Bytes(key));
        byte[] result = aes.encrypt(StrUtil.utf8Bytes(data));
        log.trace("[Kemido] |- AES crypto encrypt data, value is : [{}]", result);
        return StrUtil.utf8Str(result);
    }
}
