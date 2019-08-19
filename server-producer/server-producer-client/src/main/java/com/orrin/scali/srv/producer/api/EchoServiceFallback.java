package com.orrin.scali.srv.producer.api;

import org.springframework.stereotype.Component;

/**
 * @author orrin on 2019-08-14
 */
@Component("echoServiceFallback")
public class EchoServiceFallback implements EchoService {
    @Override
    public String echo(String string) {
        return "feign echo fallback";
    }
}
