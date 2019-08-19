package com.orrin.scali.srv.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @author orrin on 2019-08-13
 */

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.orrin.scali")
@ComponentScan("com.orrin.scali")
@SpringBootApplication
public class ConsumerAPP {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerAPP.class, args);
    }


    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }
}
