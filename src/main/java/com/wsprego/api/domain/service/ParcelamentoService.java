package com.wsprego.api.domain.service;

import com.wsprego.api.domain.exception.NegocioException;
import com.wsprego.api.domain.model.Cliente;
import com.wsprego.api.domain.model.Parcelamento;
import com.wsprego.api.domain.repository.ClienteRepository;
import com.wsprego.api.domain.repository.ParcelamentoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {

    private final ParcelamentoRepository parcelamentoRepository;
    private final CadastroClienteService cadastroClienteService;

    @Transactional
    public Parcelamento cadastrar(Parcelamento novoParcelamento){

        if (novoParcelamento.getId() != null){
            throw new NegocioException("Parcelamento a ser criado não deve possuir um código cadastrado");
        }

        Cliente cliente = cadastroClienteService.buscar(novoParcelamento.getCliente().getId());

        novoParcelamento.setCliente(cliente);
        novoParcelamento.setDataCriacao(LocalDateTime.now());

        return parcelamentoRepository.save(novoParcelamento);
    }

}
