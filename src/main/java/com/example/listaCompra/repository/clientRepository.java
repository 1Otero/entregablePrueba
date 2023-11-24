package com.example.listaCompra.repository;

import com.example.listaCompra.entity.client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface clientRepository extends JpaRepository<client, Integer> {
    Optional<client> findOneByEmail(String email);
}
