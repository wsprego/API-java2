package com.wsprego.api.controllers;

import com.wsprego.api.domain.exception.NegocioException;
import com.wsprego.api.domain.model.Parcelamento;
import com.wsprego.api.domain.repository.ParcelamentoRepository;
import com.wsprego.api.domain.service.ParcelamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final ParcelamentoRepository parcelamentoRepository;
    private final ParcelamentoService parcelamentoService;

    @GetMapping
    public List<Parcelamento> listar(){
        return parcelamentoRepository.findAll();
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<Parcelamento> buscar(@PathVariable Long parcelamentoId){
        return parcelamentoRepository.findById(parcelamentoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parcelamento cadastrar(@RequestBody Parcelamento parcelamento){
        return parcelamentoService.cadastrar(parcelamento);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturarExeção(NegocioException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
