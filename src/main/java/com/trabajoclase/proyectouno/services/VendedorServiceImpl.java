package com.trabajoclase.proyectouno.services;

import com.trabajoclase.proyectouno.models.Vendedor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class VendedorServiceImpl implements VendedorService{
    @Override
    public Flux<Vendedor> findAll() {
        return null;
    }

    @Override
    public Mono<Vendedor> findById(String id) {
        return null;
    }

    @Override
    public Mono<Vendedor> save(Vendedor vendedor) {
        return null;
    }

    @Override
    public Mono<Void> delete(Vendedor vendedor) {
        return null;
    }
}
