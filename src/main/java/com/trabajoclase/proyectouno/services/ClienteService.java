package com.trabajoclase.proyectouno.services;

import com.trabajoclase.proyectouno.models.Cliente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {
    public Flux<Cliente> findAll();

    public Mono<Cliente> findById(String id);

    public Mono<Cliente> save(Cliente cliente);

    public Mono<Void> delete(Cliente cliente);
}
