package com.orrin.scali.srv.producer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.orrin.scali.srv.producer.api.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author orrin on 2019-08-13
 */
@RestController
public class ProducerController implements EchoService {

    @Value("${user.name}")
    private String userName;

    @Autowired
    private ConfigurableEnvironment environment;

    @Override
    @SentinelResource
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string + ", userName1 = " + userName + ", userName2 = " + environment.getProperty("user.name");
    }
}
