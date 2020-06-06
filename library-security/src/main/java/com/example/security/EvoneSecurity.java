package com.example.security;

import com.blankj.utilcode.util.StringUtils;
import com.example.security.sm2.SM2EncDecUtils;
import com.example.security.sm4.SM4Utils;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.TreeMap;

/**
 * @author Jcking
 * @time 2019-11-19 15:17
 */
public class EvoneSecurity {

    private static final String DIVIDER = "\n";

    public static String auth(String verb, String body, String contentType, HashMap<String, String> headers) {
        final long timestamp = System.currentTimeMillis();
        final String authSecret = buildAuthSecret(timestamp);
        StringBuffer content = new StringBuffer();
        content.append(authSecret).append(DIVIDER);
        content.append(verb).append(DIVIDER);
        content.append(sm3(body)).append(DIVIDER);
        content.append(contentType).append(DIVIDER);
        content.append(timestamp).append(DIVIDER);
        content.append(sortHeaders(headers));

        String s1 = sm2Enc(authSecret, LogManager.e());
        String s2 = buildSignature(content.toString());

        return String.format(Locale.CHINA, "EVOne:%s:%s", s1, s2);
    }

    private static String buildSignature(String content) {
        String encode = sm4Enc(content, LogManager.i(), LogManager.d());
        return Base64.toBase64String(encode.getBytes());
    }

    private static String buildAuthSecret(long timestamp) {
        return timestamp + "evone";
    }

    private static String sortHeaders(HashMap<String, String> headers) {
        if (headers == null || headers.isEmpty()) {
            return "";
        }
        TreeMap<String, String> treeMap = new TreeMap<>(headers);
        StringBuffer sb = new StringBuffer();
        for (String key : treeMap.keySet()) {
            sb.append(key).append(treeMap.get(key));
        }
        return sb.toString();
    }

    public static String sm3(String text) {
        byte[] md = new byte[32];
        byte[] msg1 = text.getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        return new String(Hex.encode(md)).toUpperCase();
    }

    public static String sm4Enc(String text, String key1, String key2) {
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = key1;
        sm4.iv = key2;
        sm4.hexString = true;
        return sm4.encryptData_CBC(text);
    }

    public static String sm4Dec(String text, String key1, String key2) {
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = key1;
        sm4.iv = key2;
        sm4.hexString = true;
        return sm4.decryptData_CBC(text);
    }

    /**
     * sm2加密
     *
     * @param text      要加密的内容
     * @param publicKey 加密公钥
     * @return
     */
    public static String sm2Enc(String text, String publicKey) {
        try {
            if(!StringUtils.isEmpty(text)){
                return SM2EncDecUtils.encrypt(Util.hexToByte(publicKey), text.getBytes());
            }
        } catch (IOException e) {
        }
        return "";
    }

    /**
     * sm2解密
     *
     * @param text       要解密的内容
     * @param privateKey 解密私钥
     * @return
     */
    public static byte[] sm2Dec(String text, String privateKey) {
        try {
            byte[] decode = SM2EncDecUtils.decrypt(Util.hexToByte(privateKey), Util.hexToByte(text));
            return decode;
        } catch (IOException e) {
        }
        return new byte[0];
    }
}
