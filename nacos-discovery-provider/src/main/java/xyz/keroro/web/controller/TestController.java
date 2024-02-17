package xyz.keroro.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpeng
 * @since 2024年01月29日 16:41
 */
@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/test")
    public String testConnect() {
        return applicationName;
    }

    @GetMapping("/hello")
    public String hello(String name) {
        logger.info("invoked name = " + name);
        return "hello " + name;
    }
}
