package com.demo.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration(proxyBeanMethods = false)
public class RouterFunctionConfigurer {

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        // 函数式风格
        return RouterFunctions.route(this::accepted, this::handler).
                andRoute(this::acceptedEntity, this::handlerEntity);
    }

    private boolean accepted(ServerRequest request) {
        return request.uri().getPath().startsWith("/handlerFunction");
    }

    private ServerResponse handler(ServerRequest request) {
        return ServerResponse.accepted().contentType(MediaType.TEXT_PLAIN).
                body("Hell, I'm HandlerFunction");
    }


    private boolean acceptedEntity(ServerRequest request) {
        return request.uri().getPath().startsWith("/handlerEntity");
    }

    // curl -d 'name=admin&age=20' http://localhost:8081/handlerEntity
    // {"name":"test","age":0}
    private EntityResponse<User> handlerEntity(ServerRequest request) {
        return EntityResponse.fromObject(new User("test")).build();
    }
}
