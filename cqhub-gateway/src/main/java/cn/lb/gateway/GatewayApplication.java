package cn.lb.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关服务启动类
 *
 * @author RainSoul
 * @create 2024-09-08
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.lb")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
