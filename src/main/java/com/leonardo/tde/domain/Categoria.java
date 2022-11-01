package com.leonardo.tde.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

// Categoria - Tabela CATEGORIA
@Data
@Entity(name = "Categoria")
@Table(name = "CATEGORIA")
public class Categoria {

    // Atributos
    // ID - Chave Primária - Serial - Integer - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA_PK", nullable = false)
    private Integer id;

    // Nome - String - 50 Caracteres - Não Nulo - Único
    @Column(name = "NOME", length = 50, nullable = false)
    @NotEmpty(message = "O Nome do Cliente é Obrigatório!")
    @Size(max = 50, message = "O Nome do Cliente Deve Conter no Máximo 50 Caracteres!")
    private String nome;

    // Relacionamentos
    // Categoria - Chave Estrangeira - Categoria - Não Nulo
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categorias")
    private List<Produto> produtos;
}
