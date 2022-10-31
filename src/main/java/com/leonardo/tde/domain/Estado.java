package com.leonardo.tde.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

// Estado - Tabela ESTADO
@Data
@Entity(name = "Estado")
@Table(name = "ESTADO")
public class Estado {

    // Atributos
    // ID - Chave Primária - Serial - Integer - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO_PK", nullable = false, unique = true)
    private Integer id;

    // Nome - String - 50 Caracteres - Não Nulo - Único
    @Column(name = "NOME", length = 50, nullable = false, unique = true)
    @Size(max = 50, message = "O Nome do Estado Deve Conter no Máximo 50 Caracteres!")
    @NotEmpty(message = "O Nome do Estado é Obrigatório!")
    private String nome;

    // Relacionamentos
    // Cidades - Chave Estrangeira - Cidade - Não Nulo
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Cidade> cidades;
}
