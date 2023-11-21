package com.trabajoclase.proyectouno.services;

import com.trabajoclase.proyectouno.models.Cliente;
import com.trabajoclase.proyectouno.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;
    public Mono<Cliente> save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Mono<Cliente> findById(String id){
        return clienteRepository.findById(id)
                .onErrorResume(throwable -> {
                    log.error("Error al consultar cliente con id " + id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente con id = " + id + " no encontrado").getMostSpecificCause()));
    }

    public Flux<Cliente> findAll(){
        return clienteRepository.findAll()
                .onErrorResume(throwable -> {
                    log.error("Error al consultar lista de clientes", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Lista de clientes no encontrada").getMostSpecificCause()));
    }

    public Mono<Void> delete(Cliente cliente){
        return clienteRepository.delete(cliente);
    }



}
