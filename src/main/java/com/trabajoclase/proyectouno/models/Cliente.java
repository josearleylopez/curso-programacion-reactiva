package com.trabajoclase.proyectouno.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "clientes")
public class Cliente {
    @Id
    private UUID id;
    private String dni;
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String telefonoCelular;
    @Column("vendedor_id")
    private UUID vendedorId;
    private Vendedor vendedor;
}
