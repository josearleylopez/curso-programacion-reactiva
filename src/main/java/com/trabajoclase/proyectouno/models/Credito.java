package com.trabajoclase.proyectouno.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
@Table(name = "creditos")
public class Credito {
    @Id
    private UUID id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;
    private float valor;
    private float saldo;
    private Cliente cliente;
    private float tasa;
    private int cuotas;
}
