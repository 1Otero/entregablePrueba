package com.example.listaCompra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.listaCompra.entity.product;

public interface productRepository extends JpaRepository<product, Integer> {
}
