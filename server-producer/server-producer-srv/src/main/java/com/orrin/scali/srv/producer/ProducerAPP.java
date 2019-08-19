package com.orrin.scali.srv.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author orrin on 2019-08-13
 */
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.orrin.scali")
@ComponentScan("com.orrin.scali")
@SpringBootApplication
public class ProducerAPP {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ProducerAPP.class, args);
    }

}
