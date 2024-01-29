package xyz.keroro.web.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author wangpeng
 * @since 2024年01月29日 16:55
 */
@RestController
public class TestController {

    private final RestTemplate restTemplate;

    private final WebClient.Builder webClientBuilder;

    public TestController(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * 1. restTemplate方式调用其他服务
     * @return res
     */
    @GetMapping("/test")
    public String test() {
        String res = restTemplate.getForObject("http://nacos-discovery-provider/hello?name=keroro", String.class);
        return "Result: " + res;
    }

    /**
     * 2. webClient方式调用其他服务
     * @return res
     */
    @GetMapping("/webClientTest")
    public Mono<String> webClientTest() {
        return webClientBuilder.build()
                .get()
                .uri("http://nacos-discovery-provider/hello?name=keroro")
                .retrieve()
                .bodyToMono(String.class);
    }
}
