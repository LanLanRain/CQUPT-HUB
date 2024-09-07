package cn.lb.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * OSS 启动类
 *
 * @author RainSoul
 * @create 2024-09-07
 */
@SpringBootApplication
@ComponentScan("cn.lb")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
