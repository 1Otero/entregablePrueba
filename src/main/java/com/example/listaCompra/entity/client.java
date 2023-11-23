package com.example.listaCompra.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "clients")
@Getter
@Setter
@Entity
public class client{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_client;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("edad")
    private int edad;
    @JsonProperty("role")
    private String role;
}
