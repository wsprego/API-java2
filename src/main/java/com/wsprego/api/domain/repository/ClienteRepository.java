package com.wsprego.api.domain.repository;

import com.wsprego.api.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //listar por clientes com esse nome exato
    List<Cliente> findByNome(String nome);

    //listar por clientes contedo esse nome
    List<Cliente> findByNomeContaining(String nome);

    //verifivar se cliente tem o email duplicado
    Optional<Cliente> findByEmail(String email);


}
