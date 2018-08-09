package com.alpha.module_common.utils.encrypt;

import com.alpha.module_common.utils.encrypt.AES.AESType;
import com.alpha.module_common.utils.encrypt.AES.Base64;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class BackAES {

    /**
     * AES加密解决算法
     */
    // private static String sKey = "1234567890123456";
    private static String ivParameter = "1234567890123456";// 默认偏移

    private static String WAYS = "AES";
    private static String MODE = "";
    private static boolean isPwd = false;
//    private static String ModeCode = "PKCS5Padding";
//    private static int type = 0;// 默认
    private static int pwdLength = 16;
    private static String val = "0";

    private static String selectMod(int type) {
        // ECB("ECB", "0"), CBC("CBC", "1"), CFB("CFB", "2"), OFB("OFB", "3");
        String ModeCode = "PKCS5Padding";
        switch (type) {
            case 0:
                isPwd = false;
                MODE = WAYS + "/" + AESType.ECB.key() + "/" + ModeCode;

                break;
            case 1:
                isPwd = true;
                MODE = WAYS + "/" + AESType.CBC.key() + "/" + ModeCode;
                break;
            case 2:
                isPwd = true;
                MODE = WAYS + "/" + AESType.CFB.key() + "/" + ModeCode;
                break;
            case 3:
                isPwd = true;
                MODE = WAYS + "/" + AESType.OFB.key() + "/" + ModeCode;
                break;

        }

        return MODE;

    }

    /******************************** 方法一，密匙必须为16位 ********************************/
    // 加密
    public static byte[] Encrypt(String sSrc, String sKey, int type) {
        byte[] encrypted;
        try {
            sKey = toKey(sKey, pwdLength, val);
            Cipher cipher = Cipher.getInstance(selectMod(type));
            byte[] raw = sKey.getBytes();
            SecretKeySpec sKeySpec = new SecretKeySpec(raw, WAYS);

            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            if (!isPwd) {// ECB 不用密码
                cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
            }

            encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

            return Base64.encode(encrypted);// 此处使用BASE64做转码。

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;// 此处使用BASE64做转码。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey, int type) {
        sKey = toKey(sKey, pwdLength, val);
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, WAYS);
            Cipher cipher = Cipher.getInstance(selectMod(type));
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            if (!isPwd) {// ECB 不用密码
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            }
            byte[] encrypted1 = Base64.decode(sSrc.getBytes());// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, "utf-8");
        } catch (Exception ex) {
            return null;
        }
    }

    // key
    private static String toKey(String str, int strLength, String val) {

        int strLen = str.length();
        if (strLen < strLength) {
            StringBuilder strBuilder = new StringBuilder(str);
            while (strLen < strLength) {
                strBuilder.append(val);
                strLen = strBuilder.length();
            }
            str = strBuilder.toString();
        }
        return str;
    }

    /*********************************** 第二种 ***********************************************/

    public static byte[] newEncrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建AES加密编码器
            byte[] byteContent = content.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化AES加密
            return cipher.doFinal(byteContent); // AES加密结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * @param content 待解密内容,格式为byte数组
     *
     * @param password AES解密使用的密钥
     *
     * @return
     */
    public static byte[] newDecrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建AES加密编码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化AES加密
            return cipher.doFinal(content); // 得到AES解密结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成十六进制
     *
     * @param buf 二进制数组
     * @return 字符串
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (byte aBuf : buf) {
            String hex = Integer.toHexString(aBuf & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将十六进制转换为二进制
     *
     * @param hexStr 字符串
     * @return 返回二进制byte数组
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
//    public static void main(String[] args) throws Exception {
//        /*
//         * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
//         * 此处使用AES-128-CBC加密模式，key需要为16位。
//         */
//        isPwd = false;
////        defaultPwd = "admin";
//        String encryptStr = "HelloWorld!";
//        System.out.println("加密前："+encryptStr);
//        String result = BackAES.Encrypt(encryptStr);
//
//        System.out.println("加密后："+result);
//        System.out.println("解密后："+BackAES.Decrypt(result));
//    }
}
