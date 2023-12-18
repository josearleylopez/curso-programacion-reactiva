package com.trabajoclase.proyectouno.services;

import com.trabajoclase.proyectouno.models.Cuota;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuotaService {
    public Flux<Cuota> findAll(String idCredito);

    public Mono<Cuota> findById(String id);

    public Mono<Cuota> save(Cuota cuota);

    public Mono<Void> delete(Cuota cuota);

}
