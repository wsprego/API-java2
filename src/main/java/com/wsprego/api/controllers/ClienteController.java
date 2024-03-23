package com.wsprego.api.controllers;

import com.wsprego.api.domain.model.Cliente;
import com.wsprego.api.domain.repository.ClienteRepository;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@AllArgsConstructor
@RestController
public class ClienteController {

    private final ClienteRepository clienteRepository;

    //listar todos os clientes
    @GetMapping("/clientes")
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    //listar por clientes com esse nome exato
    @GetMapping("/clientes/nome")
    public List<Cliente> listarNome(){
        return clienteRepository.findByNome("João");
    }

    //listar por clientes contedo esse nome
    @GetMapping("/clientes/contendo")
    public List<Cliente> listarNomeContendo(){
        return clienteRepository.findByNomeContaining("Jo");
    }

}
