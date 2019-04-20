package com.example.bbzn.util;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @version V1.0
 * @desc AES 加密工具类
 */
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法
    public static final String AESPWD = "4536";
    public static final String IV_STRING = "c4dcef711540e3f1";
    public static final String AES_KEY = "5e1479e23f7b7d20";

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content,String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            byte[] enCodeFormat = key.getBytes();

            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");

            byte[]  initParam = IV_STRING.getBytes();

            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,ivParameterSpec);// 初始化为加密模式的密码器

            byte[] encryptedBytes = cipher.doFinal(byteContent);// 加密

            return Base64.encode(encryptedBytes,0,encryptedBytes.length);//通过Base64转码返回
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {

        try {
            byte[] encryptedBytes = Base64.decode(content);

            byte[] enCodeFormat = key.getBytes();

            SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");

            byte[] initParam = IV_STRING.getBytes();

            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, secretKey,ivParameterSpec);

            //执行操作
            byte[] result = cipher.doFinal(encryptedBytes);

            return new String(result, "utf-8");
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(password.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}