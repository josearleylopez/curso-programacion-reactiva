package com.trabajoclase.proyectouno.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
@Table(name = "cuotas")
public class Cuota {
    @Id
    private UUID id;
    private LocalDate fecha;
    private float valor;
    private float valorCapital;
    private float valorInteres;
    @Column("credito_id")
    private UUID creditoId;
}
