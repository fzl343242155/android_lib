package com.example.security.sm2;



import com.example.security.Util;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

import java.io.IOException;
import java.math.BigInteger;

public class SM2EncDecUtils {

    //数据加密
    public static String encrypt(byte[] publicKey, byte[] data) throws IOException {
        if (publicKey == null || publicKey.length == 0) {
            return null;
        }

        if (data == null || data.length == 0) {
            return null;
        }

        byte[] source = new byte[data.length];
        System.arraycopy(data, 0, source, 0, data.length);

        Cipher cipher = new Cipher();
        SM2 sm2 = SM2.Instance();
        ECPoint userKey = sm2.ecc_curve.decodePoint(publicKey);

        ECPoint c1 = cipher.Init_enc(sm2, userKey);
        cipher.Encrypt(source);
        byte[] c3 = new byte[32];
        cipher.Dofinal(c3);

//      System.out.println("C1 " + Util.byteToHex(c1.getEncoded()));
//      System.out.println("C2 " + Util.byteToHex(source));
//      System.out.println("C3 " + Util.byteToHex(c3));
        //C1 C2 C3拼装成加密字串
        // C1 | C2 | C3
        //return Util.byteToHex(c1.getEncoded()) + Util.byteToHex(source) + Util.byteToHex(c3);
        // C1 | C3 | C2
        return Util.byteToHex(c1.getEncoded()) + Util.byteToHex(c3) + Util.byteToHex(source);
    }

    //数据解密
    public static byte[] decrypt(byte[] privateKey, byte[] encryptedData) throws IOException {
        if (privateKey == null || privateKey.length == 0) {
            return null;
        }

        if (encryptedData == null || encryptedData.length == 0) {
            return null;
        }
        //加密字节数组转换为十六进制的字符串 长度变为encryptedData.length * 2
        String data = Util.byteToHex(encryptedData);
        byte[] c1Bytes = Util.hexToByte(data.substring(0, 130));
        int c2Len = encryptedData.length - 97;
        byte[] c3 = Util.hexToByte(data.substring(130, 130 + 64));
        byte[] c2 = Util.hexToByte(data.substring(194, 194 + 2 * c2Len));

        SM2 sm2 = SM2.Instance();
        BigInteger userD = new BigInteger(1, privateKey);

        //通过C1实体字节来生成ECPoint
        ECPoint c1 = sm2.ecc_curve.decodePoint(c1Bytes);
        Cipher cipher = new Cipher();
        cipher.Init_dec(userD, c1);
        cipher.Decrypt(c2);
        cipher.Dofinal(c3);

        //返回解密结果
        return c2;
    }


    public static String decode(String data) {
        String pubk = "04188B4463E4D6B943634923D192A54110B24E95A9E6C40F07323473AFAE55EF32B688FE69166AED812B44AEAC7B73577739016312F605E579CA681E07D1D2AE95";
        String result = "";
        try {
            result = SM2EncDecUtils.encrypt(Util.hexToByte(pubk), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encode(String data) {
        String prik = "596FEF7135953564C60C1C614855AC393CCEDA9427814D92B7C4D3807E5255E2";
        String result = "";
        try {
            result = new String(SM2EncDecUtils.decrypt(Util.hexToByte(prik), Util.hexToByte(data)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String sign(String data) {
        String prik = "596FEF7135953564C60C1C614855AC393CCEDA9427814D92B7C4D3807E5255E2";
        String result = "";
        try {
            SM2SignVO sign = SM2SignVerUtils.Sign2SM2(Util.hexStringToBytes(prik), data.getBytes());
            result = sign.getSm2_signForSoft();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}