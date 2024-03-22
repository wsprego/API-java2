package com.wsprego.api.controllers;

import com.wsprego.api.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {
    @GetMapping("/clientes")
    public List<Cliente> listar(){
        var cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("João");
        cliente1.setTelefone("74 9999-8888");
        cliente1.setEmail("joão@gmail");

        var cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Maria Clara");
        cliente2.setTelefone("74 8888-7777");
        cliente2.setEmail("maria@gmail");

        return Arrays.asList(cliente1,cliente2);
    }

}
