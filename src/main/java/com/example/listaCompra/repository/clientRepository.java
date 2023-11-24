package com.example.listaCompra.repository;

import com.example.listaCompra.entity.client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.Optional;

@Repository
public interface clientRepository extends JpaRepository<client, Integer> {
    Optional<client> findOneByEmail(String email);
=======
@Repository
public interface clientRepository extends JpaRepository<client, Integer> {
>>>>>>> 17e94da62ecb87f03ba4ce3a4e76897c89b02beb
}
