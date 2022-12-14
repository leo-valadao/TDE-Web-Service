package com.leonardo.tde.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

// Produto - Tabela PRODUTO
@Data
@Entity(name = "Produto")
@Table(name = "PRODUTO")
public class Produto {

    // Atributos
    // ID - Chave Primária - Serial - Integer - Não Nulo - Único
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO_PK", nullable = false)
    private Integer id;

    // Nome - String - 50 Caracteres - Não Nulo - Único
    @Column(name = "NOME", length = 50, nullable = false)
    @NotEmpty(message = "O Nome do Produto é Obrigatório!")
    @Size(max = 50, message = "O Nome do Produto Deve Conter no Máximo 50 Caracteres!")
    private String nome;

    // Preço do Produto - Double(1000000,2) - Não Nulo
    @Column(name = "PRECO", precision = 1, scale = 2)
    @NotNull(message = "O Preço do Produto é Obrigatório!")
    @Positive(message = "O Preço Deve Ser Positivo!")
    @Digits(integer = 1000000, fraction = 2, message = "O Preço Deve Ser Maior Que 0!")
    @DecimalMin(inclusive = false, value = "0", message = "O Preço Deve Ser Maior Que Zero!")
    private Double preco;

    // Relacionamentos
    // Itens do Pedido - Chave Estrangeira - ItemPedido - Não Nulo
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "produto")
    private List<ItemPedido> itensPedido;

    // Categoria - Chave Estrangeira - Categoria - Não Nulo
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "ID_CATEGORIA_FK"), inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO_FK"))
    @NotNull(message = "A(s) Categoria(s) do Produto é(são) Obrigatória(s)!")
    private List<Categoria> categorias;
}
