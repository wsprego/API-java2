package com.wsprego.api.controllers;

import com.wsprego.api.domain.exception.NegocioException;
import com.wsprego.api.domain.model.Cliente;
import com.wsprego.api.domain.repository.ClienteRepository;
import com.wsprego.api.domain.service.CadastroClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final CadastroClienteService cadastroClienteService;
    private final ClienteRepository clienteRepository;

    //listar todos os clientes
    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    //listar por clientes com esse nome exato
    @GetMapping("/buscarNome")
    public List<Cliente> listarNome(){
        return clienteRepository.findByNome("João");
    }

    //listar por clientes contedo esse nome
    @GetMapping("/buscarNomeContendo")
    public List<Cliente> listarNomeContendo(){
        return clienteRepository.findByNomeContaining("Jo");
    }

    //buacar cliente por id
    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    //adicionar um cliente
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return cadastroClienteService.salvar(cliente);
    }

    //editar cliente pelo id
    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,
                                             @Valid @RequestBody Cliente cliente){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = cadastroClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }


    //deletar o cliente pelo id
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteId){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        cadastroClienteService.excluir(clienteId);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturarExeção(NegocioException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
