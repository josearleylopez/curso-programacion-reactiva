package com.trabajoclase.proyectouno.repositories;

import com.trabajoclase.proyectouno.models.Vendedor;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface VendedorRepository extends R2dbcRepository<Vendedor, String> {
}
