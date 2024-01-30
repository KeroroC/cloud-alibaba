package xyz.keroro.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试nacos配置
 * @author wangpeng
 * @since 2024年01月30日 18:25
 */
@RestController
// @RefreshScope，让这个类下的配置内容支持动态刷新
@RefreshScope
public class ConfigController {

    @Value("${keroro.title}")
    private String title;

    @GetMapping("/title")
    public String printTitle() {
        return title;
    }
}
