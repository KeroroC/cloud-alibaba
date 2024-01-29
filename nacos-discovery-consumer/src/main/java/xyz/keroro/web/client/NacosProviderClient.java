package xyz.keroro.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangpeng
 * @since 2024年01月29日 18:01
 */
@FeignClient("nacos-discovery-provider")
public interface NacosProviderClient {

    // @RequestParam一定要明确加上，不然会将参数放在请求体中发送，导致服务端认为这是一个post请求
    @GetMapping("/hello")
    String hello(@RequestParam String name);
}
