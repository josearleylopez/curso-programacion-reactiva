package com.trabajoclase.proyectouno.repositories;

import com.trabajoclase.proyectouno.models.Credito;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface CreditoRepository extends R2dbcRepository<Credito, UUID> {
}
