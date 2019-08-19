package com.orrin.scali.srv.producer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author orrin on 2019-08-14
 */
@FeignClient(name = "service-producer", fallback = EchoServiceFallback.class)
public interface EchoService {
    @GetMapping("/echo/{string}")
    String echo(@PathVariable("string") String string);
}
