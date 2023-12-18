package com.trabajoclase.proyectouno.services;

import com.trabajoclase.proyectouno.models.Vendedor;
import com.trabajoclase.proyectouno.repositories.VendedorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@Service
@RequiredArgsConstructor
public class VendedorServiceImpl implements VendedorService{

    private final VendedorRepository vendedorRepository;
    @Override
    public Flux<Vendedor> findAll() {
        return vendedorRepository.findAll()
                .onErrorResume(throwable -> {
                    log.error("Error al consultar lista de vendedores", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Lista de vendedores no encontrada").getMostSpecificCause()));

    }

    @Override
    public Mono<Vendedor> findById(String id) {
        return vendedorRepository.findById(id)
                .onErrorResume(throwable -> {
                    log.error("Error al consultar vendedor con id " + id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Vendedor con id = " + id + " no encontrado").getMostSpecificCause()));
    }

    @Override
    public Mono<Vendedor> save(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    @Override
    public Mono<Void> delete(Vendedor vendedor) {
        return vendedorRepository.delete(vendedor);
    }
}
