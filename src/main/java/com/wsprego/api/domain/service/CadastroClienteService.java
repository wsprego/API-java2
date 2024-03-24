package com.wsprego.api.domain.service;

import com.wsprego.api.domain.exception.NegocioException;
import com.wsprego.api.domain.model.Cliente;
import com.wsprego.api.domain.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CadastroClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente buscar(Long clienteId){
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontado"));
    }

    //regra de negoçio de (adicionar/salvar) cliente
    @Transactional
    public Cliente salvar(Cliente cliente){
        //verifica se email ja existe
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .filter(c -> !c.equals(cliente))
                .isPresent();

        //lançar exeção
        if (emailEmUso){
            throw new NegocioException("Já tem um cliente cadastrado com este e-mail");
        }

        return clienteRepository.save(cliente);
    }

    //regra de negoçio de excluir cliente
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }

}
