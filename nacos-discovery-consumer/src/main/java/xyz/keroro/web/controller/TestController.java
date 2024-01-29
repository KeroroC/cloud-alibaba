package xyz.keroro.web.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangpeng
 * @since 2024年01月29日 16:55
 */
@RestController
public class TestController {

    private final RestTemplate restTemplate;

    public TestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/test")
    public String test() {
        String res = restTemplate.getForObject("http://nacos-discovery-provider/hello?name=keroro", String.class);
        return "Result: " + res;
    }
}
