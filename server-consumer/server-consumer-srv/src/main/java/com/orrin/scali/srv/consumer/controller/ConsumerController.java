package com.orrin.scali.srv.consumer.controller;

import com.orrin.scali.srv.producer.api.EchoDubboService;
import com.orrin.scali.srv.producer.api.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author orrin on 2019-08-13
 */
@RestController
public class ConsumerController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EchoService echoService;

    @Reference
    private EchoDubboService echoDubboService;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/echo/app-name")
    public String echoAppName() {
        //使用 LoadBalanceClient 和 RestTemolate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-producer");
        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/feign/echo/app-name")
    public String feignEchoAppName() {
        return echoService.echo(appName + " by feign");
    }

    @GetMapping("/dubbo/echo/app-name")
    public String dubboEchoAppName() {
        return echoDubboService.echo(appName + " by dubbo");
    }
}
