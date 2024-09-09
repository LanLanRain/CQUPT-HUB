package cn.lb.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 认证服务启动类
 *
 * @author RainSoul
 * @create 2024-09-08
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.lb")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
