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

    private final LoadBalancerClient loadBalancerClient;

    public TestController(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    @GetMapping("/test")
    public String test() {
        // 通过负载均衡选取一个实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-discovery-provider");
        String url = serviceInstance.getUri() + "/hello?name=keroro";
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(url, String.class);
        return "Invoke: " + url + ", return: " + res;
    }
}
