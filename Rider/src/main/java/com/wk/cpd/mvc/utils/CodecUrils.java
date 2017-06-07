package com.wk.cpd.mvc.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @description: 
 */
public class CodecUrils {

    private static final String PRIVATE_KEY = "huanju@game";
    private static final String IV = "2011121211143000";
    /**
     * the name of the transformation to create a cipher for.
     */
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
//    private static final String DETRANSFORMATION = "AES/CBC/PKCS7Padding";
    /**
     * 算法名称
     */
    private static final String ALGORITHM_NAME = "AES";
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static final String REQ_AD_KEY = "N4vTKJZMkpjag6FEanj_2A";
    
    public static String encryptAES(String uid, String text) throws Exception {

        // 固定前缀PRIVATE_KEY + uid
        String key = PRIVATE_KEY + uid;

        // 转化为16位key
        key = getMD5(key);
        int size = key.length();
        key = key.substring(size / 2);

        // AES加密
        byte[] data = encrypt(IV, key, text.getBytes("UTF-8"));

        // base64转化
        String encText = Base64.encodeBase64URLSafeString(data);

        return encText;
    }

    public static String decryptAES(String uid, String text) throws Exception {

        // base64解码
        byte[] decrypted = Base64.decodeBase64(text.getBytes("UTF-8"));

        // 固定前缀PRIVATE_KEY + uid
        String key = PRIVATE_KEY + uid;

        // 转化为16位key
        key = getMD5(key);
        int size = key.length();
        key = key.substring(size / 2);

        // 解密
        byte[] data = decrypt(IV, key, decrypted);

        String realText = new String(data, "UTF-8");
        return realText;
    }

    /**
     * aes 加密，AES/CBC/PKCS5Padding
     *
     * @param key     密钥字符串, 此处使用AES-128-CBC加密模式，key需要为16位
     * @param content 要加密的内容
     * @param cbcIv   初始化向量(CBC模式必须使用) 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 加密后原始二进制字符串
     * @throws Exception Exception
     */
    public static byte[] encrypt(String cbcIv, String key, byte[] content) throws Exception {

        SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes("UTF-8"),
                ALGORITHM_NAME);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);

        IvParameterSpec iv = new IvParameterSpec(cbcIv.getBytes("UTF-8"));

        cipher.init(Cipher.ENCRYPT_MODE, sksSpec, iv);

        byte[] encrypted = cipher.doFinal(content);

        return encrypted;
    }

    /**
     * aes 解密，AES/CBC/PKCS5Padding
     *
     * @param key       密钥, 此处使用AES-128-CBC加密模式，key需要为16位
     * @param encrypted 密文
     * @param cbcIv     初始化向量(CBC模式必须使用) 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 明文
     * @throws Exception 异常
     */
    public static byte[] decrypt(String cbcIv, String key, byte[] encrypted) throws Exception {

        SecretKeySpec skeSpect = new SecretKeySpec(key.getBytes("UTF-8"),
                ALGORITHM_NAME);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);

        IvParameterSpec iv = new IvParameterSpec(cbcIv.getBytes("UTF-8"));

        cipher.init(Cipher.DECRYPT_MODE, skeSpect, iv);

        byte[] decrypted = cipher.doFinal(encrypted);

        return decrypted;
    }

    public static String getMD5(String originalString) {

        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            try {
                digester.update(originalString.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] b = digester.digest();
            return bytesToHexString(b);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static String bytesToHexString(byte[] b) {

        final int CONSTANT_NUMBER0XF0 = 0xf0;
        final int CONSTANT_NUMBER0X0F = 0x0f;
        final int CONSTANT_NUMBER4 = 4;
        StringBuilder builder = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            builder.append(HEX_DIGITS[(b[i] & CONSTANT_NUMBER0XF0) >>> CONSTANT_NUMBER4]);
            builder.append(HEX_DIGITS[b[i] & CONSTANT_NUMBER0X0F]);
        }
        String string = builder.toString();
        return string;
    }
    
    /**
     * @description: sha1加密算法
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
    
    /**
     * @description: 头条token加密方法
     * @param secure_key
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String toutiaoCodec(String secure_key, String timestamp, String nonce) {
//      String[] array = {secure_key, timestamp, nonce};
//      Arrays.sort(array);
      List<String> list = new LinkedList<>();
      list.add(secure_key);
      list.add(timestamp);
      list.add(nonce);
      Collections.sort(list);
      String signature = "";
      try {
//          signature = CodecUrils.sha1(array[0] + array[1] + array[2]);
          signature = CodecUrils.sha1(list.get(0) + list.get(1) + list.get(2));
      } catch (NoSuchAlgorithmException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      return signature;
  }
}
