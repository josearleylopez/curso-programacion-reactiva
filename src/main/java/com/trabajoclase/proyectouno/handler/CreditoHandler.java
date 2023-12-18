package com.trabajoclase.proyectouno.handler;

import com.trabajoclase.proyectouno.models.Credito;
import com.trabajoclase.proyectouno.services.CreditoService;
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
public class CreditoHandler {
    private final CreditoService creditoService;
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(creditoService.findAll(), Credito.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        log.info("Buscando el credito con id: " + id);
        return creditoService.findById(id).flatMap(credito -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(credito))
                .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> create(ServerRequest request){
        Mono<Credito> credito = request.bodyToMono(Credito.class);
        log.info("Guardando credito" + credito);
        return credito.flatMap(creditoService::save).flatMap(c -> ServerResponse
                .created(URI.create("/api/creditos/".concat(c.getId().toString())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c)));
    }


    public Mono<ServerResponse> edit(ServerRequest request){
        Mono<Credito> credito = request.bodyToMono(Credito.class);
        String id = request.pathVariable("id");
        Mono<Credito> creditoDb = creditoService.findById(id);

        return creditoDb.zipWith(credito, (db, req) -> {
                    db.setSaldo(req.getSaldo());
                    db.setTasa(req.getTasa());
                    db.setCuotas(req.getCuotas());
                    return db;
                }).flatMap(c -> ServerResponse.created(URI.create("api/creditos/".concat(c.getId().toString())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(creditoService.save(c), Credito.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete (ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Credito> creditoDb = creditoService.findById(id);

        return creditoDb.flatMap(c -> creditoService.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }    
}
