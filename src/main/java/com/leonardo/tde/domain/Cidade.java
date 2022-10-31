package com.leonardo.tde.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

// Cidade - Tabela CIDADE
@Data
@Entity(name = "Cidade")
@Table(name = "CIDADE")
public class Cidade {

    // Atributos
    // ID - Chave Primária - Serial - Integer - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CIDADE_PK", nullable = false, unique = true)
    private Integer id;

    // Nome - String - 50 Caracteres - Não Nulo - Único
    @Column(name = "NOME", length = 50, nullable = false, unique = true)
    @Size(max = 50, message = "O Nome da Cidade Deve Conter no Máximo 50 Caracteres!")
    @NotEmpty(message = "O Nome da Cidade é Obrigatório!")
    private String nome;

    // Relacionamentos
    // Estado - Chave Estrangeira - Estado - Não Nulo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO_FK", referencedColumnName = "ID_ESTADO_PK")//, nullable = false)
    @NotNull(message = "O Estado é Obrigatório!")
    private Estado estado;
}
