package com.leonardo.tde.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

// Telefone - Tabela TELEFONE
@Data
@Embeddable
@Table(name = "TELEFONE")
public class Telefone {

    // Atributos
    // Número - String - 11 Caracteres - Não Nulo
    @Column(name = "NUMERO", length = 11, nullable = false)
    @NotEmpty(message = "O Telefone é Obrigatório!")
    @Size(min = 11, max = 11, message = "O Telefone Deve Ter Apenas 11 Dígitos!")
    private String numero;
}
