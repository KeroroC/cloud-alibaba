package xyz.keroro.web.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wangpeng
 * @since 2024年02月17日 17:47
 */
@Service
public class TestService {

    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    /**
     * 限流
     * @return res
     */
    @SentinelResource(value = "testService", blockHandler = "exceptionHandler")
    public String testService() {
        return "testService";
    }

    /**
     * 熔断降级
     * @return res
     */
    @SentinelResource(value = "testDegrade", fallback = "fallbackHandler")
    public String testDegrade() {
        throw new RuntimeException();
    }

    public String exceptionHandler(BlockException ex) {
        logger.error("blockHandler: ", ex);
        return null;
    }

    public String fallbackHandler() {
        logger.warn("fallbackHandler: 12312313132");
        return "123231";
    }
}
