package com.trabajoclase.proyectouno.config;

import com.trabajoclase.proyectouno.handler.ClienteHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@AllArgsConstructor
@Slf4j
public class RouterFunctionConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(ClienteHandler clienteHandler){
        return route(GET("api/clientes"), clienteHandler::findAll)
                .andRoute(GET("api/clientes/{id}"), clienteHandler::findById)
                .andRoute(POST("api/clientes"), clienteHandler::create)
                .andRoute(PUT("api/clientes/{id}"), clienteHandler::edit)
                .andRoute(DELETE("api/clientes/{id}"), clienteHandler::delete);
    }
}
