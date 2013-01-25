/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.factory;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 基于AES的加、解密工具，可对字符串和字节流加、解密。<br />
 * 默认密钥为 1234123412341234 ，如果需要更换密钥，请在使用前调用setKey函数。
 * @author lianzt
 */
public class AESFactory {

    private static final Logger log = Logger.getLogger(AESFactory.class);
    private static byte[] KEY = "1122334455667788".getBytes();
    private static Cipher cipher;
    private static SecretKeySpec skeySpec;
    private static final BASE64Encoder en = new BASE64Encoder();
    private static final BASE64Decoder de = new BASE64Decoder();

    static {
        init();
    }

    /**
     * 初始化
     */
    private static void init() {
        try {
            skeySpec = new SecretKeySpec(KEY, "AES");
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            log.error("AES error : ", e);
        } catch (NoSuchPaddingException e) {
            log.error("AES error : ", e);
        }
    }

    /**
     * 设置密钥，byte数组的长度必须为16位（128bit）。
     * @param bytes
     */
    public static void setKey(byte[] bytes) {
        if (bytes == null) {
            log.warn("密钥设置失败，参数为null，使用默认密钥");
            return;
        }
        if (bytes.length != 16) {
            log.warn("密钥设置失败，长度不为128位，使用默认密钥");
            return;
        }
        KEY = bytes;
        init();
    }

    /**
     * 加密字节流
     * @param src 明文
     * @return 密文
     * @throws Exception
     */
    public static byte[] encrypt(byte[] src) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(src);
    }

    /**
     * 解密字节流
     * @param src 密文
     * @return 明文
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(src);
    }

    /**
     * 加密字符串
     * @param src 明文
     * @return 密文
     * @throws Exception
     */
    public static String encryptString(String src) throws Exception {
        byte[] temp = encrypt(src.getBytes("UTF-8"));
        return en.encode(temp);
    }

    /**
     * 解密字符串
     * @param src 密文
     * @return 明文
     * @throws Exception
     */
    public static String decryptString(String src) throws Exception {
        byte[] temp = decrypt(de.decodeBuffer(src));
        return new String(temp, "UTF-8");
    }
}
