package com.trabajoclase.proyectouno.handler;


import com.trabajoclase.proyectouno.models.Cuota;
import com.trabajoclase.proyectouno.models.Cuota;
import com.trabajoclase.proyectouno.services.CuotaService;
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
public class CuotaHandler {
    private final CuotaService cuotaService;

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        log.info("Buscando la cuota con id: " + id);
        return cuotaService.findById(id).flatMap(cuota -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(cuota))
                .switchIfEmpty(ServerResponse.notFound().build()));
    }
    public Mono<ServerResponse> findAll(ServerRequest request){
        String idCredito = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cuotaService.findAll(idCredito), Cuota.class);
    }


    public Mono<ServerResponse> create(ServerRequest request){
        Mono<Cuota> cuota = request.bodyToMono(Cuota.class);
        log.info("Guardando cuota" + cuota);
        return cuota.flatMap(cuotaService::save).flatMap(c -> ServerResponse
                .created(URI.create("/api/cuotas/".concat(c.getId().toString())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c)));
    }

    public Mono<ServerResponse> edit(ServerRequest request){
        Mono<Cuota> cuota = request.bodyToMono(Cuota.class);
        String id = request.pathVariable("id");
        Mono<Cuota> cuotaDb = cuotaService.findById(id);

        return cuotaDb.zipWith(cuota, (db, req) -> {
                    db.setValor(req.getValor());
                    db.setValorCapital(req.getValorCapital());
                    db.setValorInteres(req.getValorInteres());
                    return db;
                }).flatMap(c -> ServerResponse.created(URI.create("api/cuotas/".concat(c.getId().toString())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(cuotaService.save(c), Cuota.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete (ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Cuota> cuotaDb = cuotaService.findById(id);

        return cuotaDb.flatMap(c -> cuotaService.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }    
}
