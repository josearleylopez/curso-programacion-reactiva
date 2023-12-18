package com.trabajoclase.proyectouno.services;

import com.trabajoclase.proyectouno.models.Cuota;
import com.trabajoclase.proyectouno.repositories.CuotaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CuotaServiceImpl implements CuotaService{
    private final CuotaRepository cuotaRepository;
    @Override
    public Flux<Cuota> findAll(String idCredito) {
        return cuotaRepository.findAllCustom(UUID.fromString(idCredito))
                .onErrorResume(throwable -> {
                    log.error("Error al consultar lista de cuotas del crédito", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Lista de cuotas no encontrada").getMostSpecificCause()));
    }

    @Override
    public Mono<Cuota> findById(String id) {
        return cuotaRepository.findById(UUID.fromString(id))
                .onErrorResume(throwable -> {
                    log.error("Error al consultar cuota con id " + id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cuota con id = " + id + " no encontrado").getMostSpecificCause()));

    }

    @Override
    public Mono<Cuota> save(Cuota cuota) {
        return cuotaRepository.save(cuota);
    }

    @Override
    public Mono<Void> delete(Cuota cuota) {
        return cuotaRepository.delete(cuota);
    }
}
