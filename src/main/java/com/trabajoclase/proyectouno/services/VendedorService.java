package com.trabajoclase.proyectouno.services;

import com.trabajoclase.proyectouno.models.Vendedor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendedorService {
    public Flux<Vendedor> findAll();

    public Mono<Vendedor> findById(String id);

    public Mono<Vendedor> save(Vendedor vendedor);

    public Mono<Void> delete(Vendedor vendedor);

}
