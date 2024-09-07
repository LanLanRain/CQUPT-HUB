package cn.lb.oss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author RainSoul
 * @create 2024-09-07
 */
@Configuration
public class MinioConfig {

    @Value("${minio.url}")
    private String url;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 配置 Minio 客户端
     *
     * 该方法用于创建并配置一个 MinioClient 实例，以便后续进行对象存储操作
     *
     * @return MinioClient 返回配置好的 Minio 客户端实例
     */
    @Bean
    public MinioClient minioClientConfig(){
        // 使用 MinioClient 的 builder 模式来构建一个 MinioClient 实例
        // 指定 Minio 服务器的 URL、访问密钥和秘密密钥
        return MinioClient.builder()
            .endpoint(url) // 设置 Minio 服务器的 URL
            .credentials(accessKey, secretKey) // 设置访问 Minio 服务器所需的访问密钥和秘密密钥
            .build(); // 构建并返回 MinioClient 实例
    }
}
