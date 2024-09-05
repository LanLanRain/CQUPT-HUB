package cn.lb.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * DruidEncryptUtil 类用于处理加密和解密操作
 *
 * @author RainSoul
 * @create 2024-09-05
 */
public class DruidEncryptUtil {
    // 公钥，用于解密
    private static String publicKey;

    // 私钥，用于加密
    private static String privateKey;

    // 静态初始化块，用于生成和初始化密钥对
    static {
        try {
            // 生成512位的密钥对
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            System.out.println("privateKey:" + privateKey);
            publicKey = keyPair[1];
            System.out.println("publicKey:" + publicKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用私钥对文本进行加密
     *
     * @param plainText 待加密的明文
     * @return 加密后的文本
     * @throws Exception 如果加密过程中发生错误
     */
    public static String encrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey, plainText);
        System.out.println("encrypt:" + encrypt);
        return encrypt;
    }

    /**
     * 使用公钥对加密文本进行解密
     *
     * @param encryptText 待解密的密文
     * @return 解密后的文本
     * @throws Exception 如果解密过程中发生错误
     */
    public static String decrypt(String encryptText) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, encryptText);
        System.out.println("decrypt:" + decrypt);
        return decrypt;
    }

    /**
     * 主函数，用于演示加密功能
     *
     * @param args 命令行参数
     * @throws Exception 如果加密过程中发生错误
     */
    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("mysql_rQ4TPf");
        System.out.println("encrypt:" + encrypt);
    }
}
