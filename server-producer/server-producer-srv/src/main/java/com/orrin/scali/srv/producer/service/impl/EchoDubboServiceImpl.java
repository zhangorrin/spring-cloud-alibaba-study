package com.orrin.scali.srv.producer.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.orrin.scali.srv.producer.api.EchoDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author orrin on 2019-08-15
 */
@org.apache.dubbo.config.annotation.Service()
public class EchoDubboServiceImpl implements EchoDubboService {

    @Value("${user.name}")
    private String userName;

    @Autowired
    private ConfigurableEnvironment environment;

    @Override
    @SentinelResource(value = "EchoDubboService.echo", blockHandler = "exceptionHandler", fallback = "echoFallback")
    public String echo(String string) {
        return "Hello Nacos Discovery and Dubbo Cloud " + string + ", userName1 = " + userName + ", userName2 = " + environment.getProperty("user.name");
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String echoFallback(long s) {
        return String.format("Halooooo %d", s);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String exceptionHandler(long s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;
    }
}
