package com.trabajoclase.proyectouno.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
@Data
@Table(name = "creditos")
public class Credito {
    @Id
    private UUID id;
    private LocalDate fecha;
    private float valor;
    private float saldo;
    private float tasa;
    private int cuotas;
    @Column("cliente_id")
    private UUID clienteId;
}
