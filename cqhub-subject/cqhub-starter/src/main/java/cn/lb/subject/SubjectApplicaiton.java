package cn.lb.subject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 刷题微服务启动类
 * @author RainSoul
 * @create 2024-09-05
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.lb")
public class SubjectApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(SubjectApplicaiton.class, args);
    }
}
