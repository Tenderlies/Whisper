/** Title: [描述产品的名称和版本]<br>
 * Description: [描述模块的功能、作用、使用方法和注意事项]<br>
 * Copyright: Copyright(c) 1991-2016<br>
 * Company: TaoShuang Tech.Co.,Ltd<br>
 * @author taoshuang
 * @version 1.0
 * @since 2016年10月31日
 */
package com.tosh.whisper.utils;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.crypto.Cipher;

/**
 * ClassName: CertificateUtil<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 * 
 * @author taoshuang
 * @since 2016年10月31日
 */
public class CertificateUtil
{
    /**
     * Description: 从KeyStore中获取私钥
     *
     * @author taoshuang
     * @see 相关函数,对于重要的函数建议注释
     * @since 2016年10月31日
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKeyFromKeyStore(String keyStorePath,
            String alias, String password) throws Exception
    {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream is = new FileInputStream(keyStorePath);
        keyStore.load(is, password.toCharArray());
        is.close();
        
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias,
                password.toCharArray());
        return privateKey;
    }
    
    /**
     * Description: 从Certificate中获取公钥
     *
     * @author taoshuang
     * @see 相关函数,对于重要的函数建议注释
     * @since 2016年10月31日
     * @param certificatePath
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKeyFromCertificate(String certificatePath)
            throws Exception
    {
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        FileInputStream is = new FileInputStream(certificatePath);
        Certificate certificate = factory.generateCertificate(is);
        PublicKey publicKey = certificate.getPublicKey();
        return publicKey;
    }
    
    /**
     * Description: 使用公钥进行加密
     *
     * @author taoshuang
     * @see 相关函数,对于重要的函数建议注释
     * @since 2016年10月31日
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, PublicKey publicKey)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
    
    /**
     * Description: 使用私钥解密
     *
     * @author taoshuang
     * @see 相关函数,对于重要的函数建议注释
     * @since 2016年10月31日
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, PrivateKey privateKey)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }
}
