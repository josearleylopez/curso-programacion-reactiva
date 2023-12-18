package com.trabajoclase.proyectouno.services;

import com.trabajoclase.proyectouno.models.Credito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditoService {
    public Flux<Credito> findAll();

    public Mono<Credito> findById(String id);

    public Mono<Credito> save(Credito credito);

    public Mono<Void> delete(Credito credito);
}
