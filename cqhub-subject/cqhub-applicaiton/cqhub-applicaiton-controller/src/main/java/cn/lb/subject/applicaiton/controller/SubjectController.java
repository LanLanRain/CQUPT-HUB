package cn.lb.subject.applicaiton.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RainSoul
 * @create 2024-09-05
 */
@RestController
public class SubjectController {

    @GetMapping("/test")
    public String index() {
        return "Hello World";
    }
}
