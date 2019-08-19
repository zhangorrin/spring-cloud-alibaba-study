package com.orrin.scali.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author orrin on 2019-08-13
 */
@SpringBootApplication
@ComponentScan("com.orrin.scali")
@EnableDiscoveryClient
public class GatewayAPP {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAPP.class, args);
    }
}
