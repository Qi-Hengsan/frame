package com.wwj.authority.util;

import com.wwj.authority.constant.JwtConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Classname JwtGenerateKeyUtil
 * @Description 根据JKS文件生产publicKey和privateKey
 * @Date 2021/6/29 13:57
 * @author by ztsong
 */
@Slf4j
public class JwtGenerateKeyUtil {

    //加载jwt.jks文件
    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks");
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;
    static {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(inputStream, JwtConstant.JWT_SECERT.toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("jwt", JwtConstant.JWT_SECERT.toCharArray());
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            log.error("JwtGenerateKeyUtil生产JWT公私钥异常:{}", e.getMessage());
        }
    }

    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

    public static PublicKey getPublicKey() {
        return publicKey;
    }

}
