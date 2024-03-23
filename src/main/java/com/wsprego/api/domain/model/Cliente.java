package com.wsprego.api.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
// @Table(name = "cliente") não é obrigatorio
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Cliente {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "nome") não é obrigatorio
    private String nome;
    private String email;
    private String telefone;

}
