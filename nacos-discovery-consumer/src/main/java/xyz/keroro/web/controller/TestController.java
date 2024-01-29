package xyz.keroro.web.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import xyz.keroro.web.client.NacosProviderClient;

/**
 * @author wangpeng
 * @since 2024年01月29日 16:55
 */
@RestController
public class TestController {

    private final RestTemplate restTemplate;

    private final WebClient.Builder webClientBuilder;

    private final NacosProviderClient nacosProviderClient;

    public TestController(RestTemplate restTemplate, WebClient.Builder webClientBuilder, NacosProviderClient nacosProviderClient) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
        this.nacosProviderClient = nacosProviderClient;
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

    /**
     * 3. feign方式调用其他服务
     * @return res
     */
    @GetMapping("/feignTest")
    public String feignTest() {
        String res = nacosProviderClient.hello("keroro");
        return "Return: " + res;
    }
}
