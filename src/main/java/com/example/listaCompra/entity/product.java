package com.example.listaCompra.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "products")
@Entity
public class product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_product;
    @JsonProperty("cantidad")
    @Column(name = "cantidad")
    private int cantidad;
    @JsonProperty("precio")
    @Column(name = "precio")
    private int precio;
    @JsonProperty("nombre")
    @Column(name = "nombre")
    private String nombre;

}
