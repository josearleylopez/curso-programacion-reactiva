package com.trabajoclase.proyectouno.config;

import com.trabajoclase.proyectouno.handler.ClienteHandler;
import com.trabajoclase.proyectouno.handler.CreditoHandler;
import com.trabajoclase.proyectouno.handler.CuotaHandler;
import com.trabajoclase.proyectouno.handler.VendedorHandler;
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
    public RouterFunction<ServerResponse> clientesRoutes(ClienteHandler clienteHandler){
        return route(GET("api/clientes"), clienteHandler::findAll)
                .andRoute(GET("api/clientes/{id}"), clienteHandler::findById)
                .andRoute(POST("api/clientes"), clienteHandler::create)
                .andRoute(PUT("api/clientes/{id}"), clienteHandler::edit)
                .andRoute(DELETE("api/clientes/{id}"), clienteHandler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> vendedoresRoutes(VendedorHandler vendedorHandler){
        return route(GET("api/vendedores"), vendedorHandler::findAll)
                .andRoute(GET("api/vendedores/{id}"), vendedorHandler::findById)
                .andRoute(POST("api/vendedores"), vendedorHandler::create)
                .andRoute(PUT("api/vendedores/{id}"), vendedorHandler::edit)
                .andRoute(DELETE("api/vendedores/{id}"), vendedorHandler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> creditosRoutes(CreditoHandler creditoHandler){
        return route(GET("api/creditos"), creditoHandler::findAll)
                .andRoute(GET("api/creditos/{id}"), creditoHandler::findById)
                .andRoute(POST("api/creditos"), creditoHandler::create)
                .andRoute(PUT("api/creditos/{id}"), creditoHandler::edit)
                .andRoute(DELETE("api/creditos/{id}"), creditoHandler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> cuotasRoutes(CuotaHandler cuotaHandler){
        return route(GET("api/cuotas/{id}"), cuotaHandler::findAll)
                .andRoute(POST("api/cuotas"), cuotaHandler::create)
                .andRoute(PUT("api/cuotas/{id}"), cuotaHandler::edit)
                .andRoute(DELETE("api/cuotas/{id}"), cuotaHandler::delete);
    }
}
