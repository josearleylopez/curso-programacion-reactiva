package com.trabajoclase.proyectouno.services;

import com.trabajoclase.proyectouno.models.Cliente;
import com.trabajoclase.proyectouno.repositories.ClienteRepository;
import com.trabajoclase.proyectouno.repositories.VendedorRepository;
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
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    public Mono<Cliente> save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Mono<Cliente> findById(String id){
        return clienteRepository.findByIdCustom(UUID.fromString(id))
                .onErrorResume(throwable -> {
                    log.error("Error al consultar cliente con id " + id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente con id = " + id + " no encontrado").getMostSpecificCause()))
                .flatMap(cliente -> {
                    if (cliente.getVendedorId() == null){
                        return Mono.just(cliente);
                    }
                    return vendedorRepository.findById(cliente.getVendedorId().toString()).map(vendedor -> {
                        cliente.setVendedor(vendedor);
                        return cliente;
                    });
                });
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
