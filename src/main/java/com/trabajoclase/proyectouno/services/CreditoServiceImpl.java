package com.trabajoclase.proyectouno.services;

import com.trabajoclase.proyectouno.models.Credito;
import com.trabajoclase.proyectouno.repositories.CreditoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditoServiceImpl implements CreditoService{
    private final CreditoRepository creditoRepository;
    @Override
    public Flux<Credito> findAll() {
        return creditoRepository.findAll()
                .onErrorResume(throwable -> {
                    log.error("Error al consultar lista de creditos", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Lista de creditos no encontrada").getMostSpecificCause()));
    }

    @Override
    public Mono<Credito> findById(String id) {
        return creditoRepository.findById(UUID.fromString(id))
                .onErrorResume(throwable -> {
                    log.error("Error al consultar credito con id " + id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Credito con id = " + id + " no encontrado").getMostSpecificCause()));

    }

    @Override
    public Mono<Credito> save(Credito credito) {
        log.info("Credito en repository "+ credito.toString());
        return creditoRepository.save(credito);
    }

    @Override
    public Mono<Void> delete(Credito credito) {
        return creditoRepository.delete(credito);
    }
}
