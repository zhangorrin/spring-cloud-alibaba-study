package com.orrin.scali.common.frame.web;


import com.orrin.scali.common.model.core.GlobalResponse;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.reactive.result.view.ViewResolver;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author orrin on 2019-08-14
 */
@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, ApplicationContext applicationContext,
                                          ServerCodecConfigurer serverCodecConfigurer, ObjectProvider<ViewResolver> viewResolvers) {
        super(errorAttributes, new ResourceProperties(), applicationContext);
        super.setViewResolvers(viewResolvers.orderedStream().collect(Collectors.toList()));
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {

        final Map<String, Object> errorPropertiesMap = getErrorAttributes(request, true);

        final GlobalResponse globalResponse = GlobalResponse.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统异常", errorPropertiesMap);

        return ServerResponse.status(errorPropertiesMap.containsKey("status") ? Integer.parseInt(errorPropertiesMap.get("status").toString()) : HttpStatus.INTERNAL_SERVER_ERROR.value())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(globalResponse.toMap()));
    }
}
