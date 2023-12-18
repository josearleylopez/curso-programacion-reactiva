package com.trabajoclase.proyectouno.repositories;

import com.trabajoclase.proyectouno.models.Cliente;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ClienteRepository extends R2dbcRepository<Cliente, String> {
    Flux<Cliente> findClienteByNombresContainsIgnoreCaseOrderByNombresAsc(String nombre);
    @Query("select id, dni, nombres, apellidos, correo_electronico, telefono_celular, vendedor_id from clientes where id=$1")
    Mono<Cliente> findByIdCustom(UUID id);

}
