/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lianzt.factory;

import com.lianzt.util.StringUtil;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 当系统中需要使用多个密钥进行加/解密操作时可使用该对象
 * 需要注意，该对象与AESFactory加密的结果不同
 * @author lianzt
 */
public class CryptBean {

    private static final Logger log = Logger.getLogger(AESFactory.class);
    private byte[] key = "1122334455667788".getBytes();
    private Cipher cipher;
    private SecretKeySpec skeySpec;
    private final BASE64Encoder en = new BASE64Encoder();
    private final BASE64Decoder de = new BASE64Decoder();

    /**
     * 禁止实例化
     */
    private CryptBean() {
    }

    /**
     * 获取加/解密bean
     * @param key 密钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public static CryptBean getCryptBean(String key) throws
            NoSuchAlgorithmException, NoSuchPaddingException {
        //对密钥做位移运算
        if (StringUtil.isNull(key)) {
            log.warn("密钥设置失败，参数为null");
            throw new RuntimeException("密钥为空");
        }
        byte[] bytes = key.getBytes();
        if (bytes.length != 16) {
            log.warn("密钥设置失败，长度不为128位");
            throw new RuntimeException("密钥长度错误");
        }
        CryptBean b = new CryptBean();
        b.key = new byte[16];
        System.arraycopy(bytes, 6, b.key, 0, 2);
        System.arraycopy(bytes, 2, b.key, 2, 2);
        System.arraycopy(bytes, 0, b.key, 4, 2);
        System.arraycopy(bytes, 4, b.key, 6, 2);
        System.arraycopy(bytes, 10, b.key, 8, 2);
        System.arraycopy(bytes, 14, b.key, 10, 2);
        System.arraycopy(bytes, 12, b.key, 12, 2);
        System.arraycopy(bytes, 8, b.key, 14, 2);
        b.init();
        return b;
    }

    /**
     * 初始化
     */
    private void init() throws NoSuchAlgorithmException, NoSuchPaddingException {
        skeySpec = new SecretKeySpec(key, "AES");
        cipher = Cipher.getInstance("AES");
    }

    /**
     * 加密字节流
     * @param src 明文
     * @return 密文
     * @throws Exception
     */
    public byte[] encrypt(byte[] src) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(src);
    }

    /**
     * 解密字节流
     * @param src 密文
     * @return 明文
     * @throws Exception
     */
    public byte[] decrypt(byte[] src) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(src);
    }

    /**
     * 加密字符串
     * @param src 明文
     * @return 密文
     * @throws Exception
     */
    public String encryptString(String src) throws Exception {
        byte[] temp = encrypt(src.getBytes("UTF-8"));
        return en.encode(temp);
    }

    /**
     * 解密字符串
     * @param src 密文
     * @return 明文
     * @throws Exception
     */
    public String decryptString(String src) throws Exception {
        byte[] temp = decrypt(de.decodeBuffer(src));
        return new String(temp, "UTF-8");
    }
}
