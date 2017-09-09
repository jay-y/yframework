package org.yframework.toolkit;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Description: RSACrypto.<br>
 * Date: 2017/7/9 17:30<br>
 */
public enum RSACrypto
{
    INSTANCE;

    private final Logger log = LoggerFactory.getLogger(RSACrypto.class);

    /**
     * 字节数据转字符串专用集合
     */
    private final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 加载KEY
     *
     * @param in
     * @return 提取后的字符串
     */
    public String loadKey(InputStream in) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null)
        {
            if (readLine.charAt(0) != '-')
            {
                sb.append(readLine);
                sb.append('\r');
            }
        }
        return sb.toString();
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param in 公钥输入流
     * @throws RuntimeException 加载公钥时产生的异常
     */
    public RSAPublicKey getPublicKey(InputStream in) throws RuntimeException
    {
        try
        {
            return this.getPublicKey(loadKey(in));
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("公钥数据流读取错误");
        }
        catch (NullPointerException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("公钥输入流为空");
        }
    }


    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws RuntimeException 加载公钥时产生的异常
     */
    public RSAPublicKey getPublicKey(String publicKeyStr) throws RuntimeException
    {
        try
        {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("无此算法");
        }
        catch (InvalidKeySpecException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("公钥非法");
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("公钥数据内容读取错误");
        }
        catch (NullPointerException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("公钥数据为空");
        }
    }

    /**
     * 从文件中加载私钥
     *
     * @param in 私钥输入流
     * @return RSAPrivateKey
     * @throws RuntimeException
     */
    public RSAPrivateKey getPrivateKey(InputStream in) throws RuntimeException
    {
        try
        {
            return getPrivateKey(loadKey(in));
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("私钥数据读取错误");
        }
        catch (NullPointerException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("私钥输入流为空");
        }
    }

    public RSAPrivateKey getPrivateKey(String privateKeyStr) throws RuntimeException
    {
        try
        {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(keySpec);
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("无此算法");
        }
        catch (InvalidKeySpecException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("私钥非法");
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("私钥数据内容读取错误");
        }
        catch (NullPointerException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("私钥数据为空");
        }
    }

    /**
     * 公钥加密
     *
     * @param publicKey 公钥
     * @param content   需要加密内容
     * @return 密文
     */
    public String encryptWithBase64(RSAPublicKey publicKey, String content)
    {
        byte[] cipher = this.encrypt(publicKey, content.getBytes());
        return Base64.encodeBase64String(cipher);
    }

    /**
     * 加密过程
     *
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return
     * @throws RuntimeException 加密过程中的异常信息
     */
    public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws RuntimeException
    {
        if (publicKey == null)
        {
            throw new RuntimeException("加密公钥为空, 请设置");
        }
        try
        {
            Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("无此加密算法");
        }
        catch (NoSuchPaddingException e)
        {
            log.error(e.getMessage(), e);
            return null;
        }
        catch (InvalidKeyException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("加密公钥非法,请检查");
        }
        catch (IllegalBlockSizeException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("明文长度非法");
        }
        catch (BadPaddingException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("明文数据已损坏");
        }
    }

    /**
     * 私钥解密
     *
     * @param privateKey 私钥
     * @param content    密文
     * @return 明文
     */
    public String decryptWithBase64(RSAPrivateKey privateKey, String content)
    {
        //私钥解密
        byte[] plainText = this.decrypt(privateKey, Base64.decodeBase64(content));
        return new String(plainText);
    }

    /**
     * 解密过程
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws RuntimeException 解密过程中的异常信息
     */
    public byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws RuntimeException
    {
        if (privateKey == null)
        {
            throw new RuntimeException("解密私钥为空, 请设置");
        }
        try
        {
            Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(cipherData);
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("无此解密算法");
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (InvalidKeyException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("解密私钥非法,请检查");
        }
        catch (IllegalBlockSizeException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("密文长度非法");
        }
        catch (BadPaddingException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("密文数据已损坏");
        }
    }

    public String sign(PrivateKey priKey, String content) throws RuntimeException
    {
        return this.sign(priKey, content, null);
    }

    /**
     * rsa签名(SHA1WithRSA)
     *
     * @param priKey  rsa私钥
     * @param content 待签名的字符串
     * @param charset 字符编码
     * @return 签名结果
     * @throws RuntimeException 签名过程中的异常信息
     */
    public String sign(PrivateKey priKey, String content, String charset) throws RuntimeException
    {
        try
        {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            signature.update(getBytes(content, charset));

            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64(signed));
        }
        catch (SignatureException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("签名工具获取失败");
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("无此摘要生成算法");
        }
        catch (InvalidKeyException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("签名私钥非法,请检查");
        }
        catch (UnsupportedEncodingException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("编码不支持");
        }
    }

    public boolean check(PublicKey pubKey, String content, String sign) throws RuntimeException
    {
        return this.check(pubKey, content, sign, null);
    }

    /**
     * rsa验签
     *
     * @param pubKey  rsa公钥
     * @param content 被签名的内容
     * @param sign    签名后的结果
     * @param charset 字符集
     * @return 验签结果
     * @throws RuntimeException 验签失败，则抛异常
     */
    public boolean check(PublicKey pubKey, String content, String sign, String charset) throws RuntimeException
    {
        try
        {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(pubKey);
            signature.update(getBytes(content, charset));
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        }
        catch (SignatureException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("签名工具获取失败");
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("无此摘要生成算法");
        }
        catch (InvalidKeyException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("验签公钥非法,请检查");
        }
        catch (UnsupportedEncodingException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("编码不支持");
        }
    }

    /**
     * 获取字节数组
     *
     * @param content
     * @param charset
     * @return 字节数组
     * @throws UnsupportedEncodingException
     */
    public byte[] getBytes(String content, String charset) throws UnsupportedEncodingException
    {
        if (StringUtil.isEmpty(charset))
        {
            return content.getBytes();
        }
        return content.getBytes(charset);
    }


    /**
     * 字节数据转十六进制字符串
     *
     * @param data 输入数据
     * @return 十六进制内容
     */
    public String byteArrayToString(byte[] data)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++)
        {
            //取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            stringBuilder.append(HEX_CHARS[(data[i] & 0xf0) >>> 4]);
            //取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHARS[(data[i] & 0x0f)]);
            if (i < data.length - 1)
            {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }
}
