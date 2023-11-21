package com.trabajoclase.proyectouno.repositories;

import com.trabajoclase.proyectouno.models.Cliente;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ClienteRepository extends R2dbcRepository<Cliente, String> {
    Flux<Cliente> findClienteByNombresContainsIgnoreCaseOrderByNombresAsc(String nombre);

}
