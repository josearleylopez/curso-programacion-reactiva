package com.trabajoclase.proyectouno.repositories;

import com.trabajoclase.proyectouno.models.Cuota;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;


import java.util.UUID;

public interface CuotaRepository extends R2dbcRepository<Cuota, UUID> {
    @Query("select id, fecha, valor, valor_capital, valor_interes, credito_id from cuotas where credito_id=$1")
    Flux<Cuota> findAllCustom(UUID id);
}
