package com.trabajoclase.proyectouno.handler;

import com.trabajoclase.proyectouno.models.Cliente;
import com.trabajoclase.proyectouno.services.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClienteHandler {
    private final ClienteService clienteService;
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(clienteService.findAll(), Cliente.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        log.info("Buscando el cliente con id: " + id);
        return clienteService.findById(id).flatMap(cliente -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(cliente))
                .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> create(ServerRequest request){
        Mono<Cliente> cliente = request.bodyToMono(Cliente.class);
        return cliente.flatMap(clienteService::save).flatMap(c -> ServerResponse
                .created(URI.create("/api/clientes/".concat(c.getId().toString())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c)));
    }


    public Mono<ServerResponse> edit(ServerRequest request){
        Mono<Cliente> cliente = request.bodyToMono(Cliente.class);
        String id = request.pathVariable("id");
        Mono<Cliente> clienteDb = clienteService.findById(id);

        return clienteDb.zipWith(cliente, (db, req) -> {
            db.setNombres(req.getNombres());
            db.setApellidos(req.getApellidos());
            db.setTelefonoCelular(req.getTelefonoCelular());
            return db;
        }).flatMap(c -> ServerResponse.created(URI.create("api/clientes/".concat(c.getId().toString())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(clienteService.save(c), Cliente.class))
        .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete (ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Cliente> clienteDb = clienteService.findById(id);

        return clienteDb.flatMap(c -> clienteService.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
