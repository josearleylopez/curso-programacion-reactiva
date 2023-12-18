package com.trabajoclase.proyectouno.handler;

import com.trabajoclase.proyectouno.models.Vendedor;
import com.trabajoclase.proyectouno.services.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
@Component
@RequiredArgsConstructor
public class VendedorHandler {
    private final VendedorService vendedorService;
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(vendedorService.findAll(), Vendedor.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        return vendedorService.findById(id).flatMap(vendedor -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(vendedor))
                .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> create(ServerRequest request){
        Mono<Vendedor> vendedor = request.bodyToMono(Vendedor.class);
        return vendedor.flatMap(vendedorService::save).flatMap(c -> ServerResponse
                .created(URI.create("/api/vendedores/".concat(c.getId().toString())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c)));
    }


    public Mono<ServerResponse> edit(ServerRequest request){
        Mono<Vendedor> vendedor = request.bodyToMono(Vendedor.class);
        String id = request.pathVariable("id");
        Mono<Vendedor> vendedorDb = vendedorService.findById(id);

        return vendedorDb.zipWith(vendedor, (db, req) -> {
                    db.setNombres(req.getNombres());
                    db.setApellidos(req.getApellidos());
                    db.setTelefonoCelular(req.getTelefonoCelular());
                    return db;
                }).flatMap(c -> ServerResponse.created(URI.create("api/vendedores/".concat(c.getId().toString())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(vendedorService.save(c), Vendedor.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete (ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Vendedor> vendedorDb = vendedorService.findById(id);

        return vendedorDb.flatMap(c -> vendedorService.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }    
}
