package com.wsprego.api.domain.repository;

import com.wsprego.api.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome); //listar por clientes com esse nome exato

    List<Cliente> findByNomeContaining(String nome); //listar por clientes contedo esse nome

}
